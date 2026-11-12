package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.dto.flight.FlightRequestDTO;
import it.lorenzolavacchini.dedalo.dto.flight.FlightResponseDTO;
import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import it.lorenzolavacchini.dedalo.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("users/{userId}/pilot/logbook/flights")
    public ResponseEntity<List<FlightResponseDTO>> getAllByUserId(@PathVariable Long userId){

        List<FlightResponseDTO> flightsDTO = flightService.getAllByUserId(userId);

        return ResponseEntity.ok(flightsDTO);
    }

    @PostMapping("users/{userId}/pilot/logbook/flights")
    public ResponseEntity<FlightResponseDTO> create(@PathVariable Long userId,
                                                          @Valid @RequestBody FlightRequestDTO flightRequestDTO){

        FlightResponseDTO flightResponseDTO = flightService.create(userId, flightRequestDTO);

        return ResponseEntity.ok(flightResponseDTO);
    }

}
