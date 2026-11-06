package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.rental.RentalRequestDTO;
import it.lorenzolavacchini.dedalo.dto.rental.RentalResponseDTO;
import it.lorenzolavacchini.dedalo.dto.timeslots.TimeSlotDTO;
import it.lorenzolavacchini.dedalo.exception.*;
import it.lorenzolavacchini.dedalo.mapper.AirplaneMapper;
import it.lorenzolavacchini.dedalo.mapper.RentalMapper;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import it.lorenzolavacchini.dedalo.repository.RentableAirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final PilotRepository pilotRepository;
    private final RentableAirplaneRepository rentableAirplaneRepository;
    private final RentalMapper rentalMapper;
    private final PilotService pilotService;
    private final RentableAirplaneService rentableAirplaneService;
    private final AirplaneService airplaneService;
    private final AirplaneMapper airplaneMapper;

    public RentalService(RentalRepository rentalRepository, PilotRepository pilotRepository, RentableAirplaneRepository rentableAirplaneRepository, RentalMapper rentalMapper, PilotService pilotService, RentableAirplaneService rentableAirplaneService, AirplaneService airplaneService, AirplaneMapper airplaneMapper) {
        this.rentalRepository = rentalRepository;
        this.pilotRepository = pilotRepository;
        this.rentableAirplaneRepository = rentableAirplaneRepository;
        this.rentalMapper = rentalMapper;
        this.pilotService = pilotService;
        this.rentableAirplaneService = rentableAirplaneService;
        this.airplaneService = airplaneService;
        this.airplaneMapper = airplaneMapper;
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

    @Transactional
    public RentalResponseDTO create(RentalRequestDTO request) {

        if(!rentableAirplaneService.exists(request.getAirplaneId())){
            throw new RentableAirplaneNotFoundException();
        }

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


        if (request.getStartTime().getMinute() != 0 || request.getEndTime().getMinute() != 0){
            throw new MinutesNotZeroException();
        }


        rentableAirplaneService.getById(request.getAirplaneId());

        airplaneService.getById(request.getAirplaneId());

        if (!pilotService.canPilotAirplane(rentableAirplaneService.getById(request.getAirplaneId()), pilotService.getById(request.getPilotId()))){
            throw new PilotNotAbilitatedForThisAirplaneException();
        }

        List<Rental> existingRentals = rentalRepository.findByRentedAirplane_IdAndDate(request.getAirplaneId(), request.getDate());

        for (Rental existing : existingRentals) {

            if (request.getStartTime().isBefore(existing.getEndTime()) &&
                    request.getEndTime().isAfter(existing.getStartTime())) {
                throw new IllegalArgumentException("L'aeroplano è già occupato in questa fascia oraria");
            }
        }

        Pilot pilot = pilotRepository.findById(request.getPilotId())
                .orElseThrow(() -> new PilotNotFoundException());

        RentableAirplane airplane = rentableAirplaneRepository.findById(request.getAirplaneId())
                .orElseThrow(() -> new EntityNotFoundException("Aeroplano non trovato"));


        Rental rental = new Rental();
        rental.setRenterPilot(pilot);
        rental.setRentedAirplane(airplane);
        rental.setDate(request.getDate());
        rental.setStartTime(request.getStartTime());
        rental.setEndTime(request.getEndTime());
        rental.setBookedAt(LocalDateTime.now());
        rental.setTotalRate(calculateTotalRate(airplane, rental.getStartTime(), rental.getEndTime()));

        rentalRepository.save(rental);

        RentalResponseDTO rentalResponseDTO = rentalMapper.toRentalResponseDTO(rental);

        return rentalResponseDTO;
    }

    private int calculateTotalRate(RentableAirplane rentableAirplane, LocalTime startTime, LocalTime endTime){

        Duration duration = Duration.between(startTime, endTime);
        Long hoursDuration = duration.toHours();
        int totalRate = Math.toIntExact(rentableAirplane.getHourlyRate() * hoursDuration);
        return totalRate;

    }
}
