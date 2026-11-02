package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.rental.RentalRequestDTO;
import it.lorenzolavacchini.dedalo.dto.rental.RentalResponseDTO;
import it.lorenzolavacchini.dedalo.dto.timeslots.TimeSlotDTO;
import it.lorenzolavacchini.dedalo.exception.PastDateException;
import it.lorenzolavacchini.dedalo.mapper.RentalMapper;
import it.lorenzolavacchini.dedalo.repository.AirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import it.lorenzolavacchini.dedalo.repository.RentableAirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final PilotRepository pilotRepository;
    private final AirplaneRepository airplaneRepository;
    private final RentableAirplaneRepository rentableAirplaneRepository;
    private final RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, PilotRepository pilotRepository, AirplaneRepository airplaneRepository, RentableAirplaneRepository rentableAirplaneRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.pilotRepository = pilotRepository;
        this.airplaneRepository = airplaneRepository;
        this.rentableAirplaneRepository = rentableAirplaneRepository;
        this.rentalMapper = rentalMapper;
    }

    public List<TimeSlotDTO> getAvailableSlots(Long airplaneId, LocalDate date) {

        if (date.isBefore(LocalDate.now())) {
            throw new PastDateException();
        }

        List<Rental> rentals = rentalRepository.findByRentedAirplane_IdAndDate(airplaneId, date);


        boolean[] busyHours = new boolean[24];


        for (Rental rental : rentals) {

            int startHour = rental.getStartTime().getHour();
            int endHour = rental.getEndTime().getHour();

            for (int h = startHour; h < endHour; h++) {
                if (h >= 8 && h < 20) {
                    busyHours[h] = true;
                }
            }
        }

        List<TimeSlotDTO> timeslotsDTO = new ArrayList<>();
        int startFree = -1;


        for (int h = 8; h <= 20; h++) {
            if (h < 20 && !busyHours[h]) {

                if (startFree == -1) {
                    startFree = h;
                }
            } else {

                if (startFree != -1) {

                    TimeSlotDTO slot = new TimeSlotDTO();
                    LocalTime startTime = LocalTime.of(startFree, 0);
                    LocalTime endTime = LocalTime.of(h, 0);
                    slot.setStartTime(startTime);
                    slot.setEndTime(endTime);

                    timeslotsDTO.add(slot);
                    startFree = -1;
                }
            }
        }

        return timeslotsDTO;
    }

    public RentalResponseDTO create(RentalRequestDTO request) {

        if (request.getDate().isBefore(LocalDate.now())) {
            throw new PastDateException();
        }


        if (request.getDate().isEqual(LocalDate.now()) && request.getStartTime().isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("L'orario di inizio non può essere nel passato");
        }


        if (!request.getStartTime().isBefore(request.getEndTime())) {
            throw new IllegalArgumentException("L'orario di inizio deve essere precedente a quello di fine");
        }


        LocalTime limitStart = LocalTime.of(8, 0);
        LocalTime limitEnd = LocalTime.of(20, 0);
        if (request.getStartTime().isBefore(limitStart) || request.getEndTime().isAfter(limitEnd)) {
            throw new IllegalArgumentException("I noleggi sono consentiti solo dalle 08:00 alle 20:00");
        }


        List<Rental> existingRentals = rentalRepository.findByRentedAirplane_IdAndDate(request.getAirplaneId(), request.getDate());

        for (Rental existing : existingRentals) {

            if (request.getStartTime().isBefore(existing.getEndTime()) &&
                    request.getEndTime().isAfter(existing.getStartTime())) {
                throw new IllegalArgumentException("L'aeroplano è già occupato in questa fascia oraria");
            }
        }


        Pilot pilot = pilotRepository.findById(request.getPilotId())
                .orElseThrow(() -> new EntityNotFoundException("Pilota non trovato"));

        RentableAirplane airplane = rentableAirplaneRepository.findById(request.getAirplaneId())
                .orElseThrow(() -> new EntityNotFoundException("Aeroplano non trovato"));


        Rental rental = new Rental();
        rental.setRenterPilot(pilot);
        rental.setRentedAirplane(airplane);
        rental.setDate(request.getDate());
        rental.setStartTime(request.getStartTime());
        rental.setEndTime(request.getEndTime());
        rental.setBookedAt(LocalDateTime.now());

        rentalRepository.save(rental);

        RentalResponseDTO rentalResponseDTO = rentalMapper.toRentalResponseDTO(rental);
        return rentalResponseDTO;
    }

}
