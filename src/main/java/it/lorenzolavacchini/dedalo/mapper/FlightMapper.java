package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import it.lorenzolavacchini.dedalo.domain.Flight;
import it.lorenzolavacchini.dedalo.domain.Logbook;
import it.lorenzolavacchini.dedalo.dto.flight.FlightRequestDTO;
import it.lorenzolavacchini.dedalo.dto.flight.FlightResponseDTO;
import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightResponseDTO toFlightResponseDTO(Flight flight){

        if(flight == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        FlightResponseDTO flightResponseDTO = new FlightResponseDTO();

        flightResponseDTO.setAirplaneId(flight.getAirplaneUsed().getId());
        flightResponseDTO.setFlightDate(flight.getFlightDate());
        flightResponseDTO.setDepartureTime(flight.getDepartureTime());
        flightResponseDTO.setArrivalTime(flight.getArrivalTime());
        flightResponseDTO.setDepartureAirfield(flight.getDepartureAirfield());
        flightResponseDTO.setArrivalAirfield(flight.getArrivalAirfield());
        flightResponseDTO.setNotes(flight.getNotes());

        return flightResponseDTO;
    }


    public Flight toEntity(FlightRequestDTO flightRequestDTO, Airplane airplaneUsed, Logbook logbook){

        if(flightRequestDTO == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        Flight flight = new Flight();

        flight.setLogbook(logbook);
        flight.setAirplaneUsed(airplaneUsed);
        flight.setFlightDate(flightRequestDTO.getFlightDate());
        flight.setDepartureTime(flightRequestDTO.getDepartureTime());
        flight.setArrivalTime(flightRequestDTO.getArrivalTime());
        flight.setDepartureAirfield(flightRequestDTO.getDepartureAirfield());
        flight.setArrivalAirfield(flightRequestDTO.getArrivalAirfield());
        flight.setNotes(flightRequestDTO.getNotes());

        return flight;
    }


}
