package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.flight.FlightRequestDTO;
import it.lorenzolavacchini.dedalo.dto.flight.FlightResponseDTO;
import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import it.lorenzolavacchini.dedalo.exception.AirplaneNotFoundException;
import it.lorenzolavacchini.dedalo.exception.LogbookIsNullException;
import it.lorenzolavacchini.dedalo.exception.UserIsNotPilotException;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.FlightMapper;
import it.lorenzolavacchini.dedalo.repository.AirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.FlightRepository;
import it.lorenzolavacchini.dedalo.repository.LogbookRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final FlightMapper flightMapper;
    private final LogbookService logbookService;
    private final LogbookRepository logbookRepository;
    private final AirplaneRepository airplaneRepository;

    public FlightService(FlightRepository flightRepository, UserRepository userRepository, FlightMapper flightMapper, LogbookService logbookService, LogbookRepository logbookRepository, AirplaneRepository airplaneRepository) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.flightMapper = flightMapper;
        this.logbookService = logbookService;
        this.logbookRepository = logbookRepository;
        this.airplaneRepository = airplaneRepository;
    }


    public List<FlightResponseDTO> getAllByUserId(Long userId){

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Pilot pilot = user.getPilotRole();

        if (pilot == null){
            throw new UserIsNotPilotException();
        }

        Logbook logbook = pilot.getLogbook();

        if (logbook == null){
            throw new LogbookIsNullException();
        }

        List<Flight> flights = logbook.getFlights();

        List<FlightResponseDTO> flightsDTO = new ArrayList<>();

        for (Flight flight : flights){

            flightsDTO.add(flightMapper.toFlightResponseDTO(flight));

        }

        return flightsDTO;
    }

    @Transactional
    public FlightResponseDTO create(Long userId, FlightRequestDTO flightRequestDTO){

        Airplane airplaneUsed = airplaneRepository.findByIdAndDeletedFalse(flightRequestDTO.getAirplaneId())
                .orElseThrow(() -> new AirplaneNotFoundException());

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Pilot pilot = user.getPilotRole();

        if (pilot == null){
            throw new UserIsNotPilotException();
        }

        Logbook logbook = pilot.getLogbook();

        if (logbook == null){
            throw new LogbookIsNullException();
        }

        Flight flight = flightMapper.toEntity(flightRequestDTO, airplaneUsed, logbook);

        addFlight(logbook, flight);

        flightRepository.save(flight);
        logbookRepository.save(logbook);

        FlightResponseDTO flightResponseDTO = flightMapper.toFlightResponseDTO(flight);

        return flightResponseDTO;
    }

    public void addFlight(Logbook logbook, Flight flight){

        logbook.getFlights().add(flight);

    }

}
