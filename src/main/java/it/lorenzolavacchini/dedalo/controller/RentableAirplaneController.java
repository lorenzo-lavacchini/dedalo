package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.domain.RentableAirplane;
import it.lorenzolavacchini.dedalo.dto.airplane.RentableAirplaneResponseDTO;
import it.lorenzolavacchini.dedalo.service.RentableAirplaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rentable-airplanes")
public class RentableAirplaneController {

    private final RentableAirplaneService rentableAirplaneService;

    public RentableAirplaneController(RentableAirplaneService rentableAirplaneService) {
        this.rentableAirplaneService = rentableAirplaneService;
    }

    @GetMapping
    public ResponseEntity<List<RentableAirplaneResponseDTO>> getRentableAirplanes(){
        List<RentableAirplaneResponseDTO> airplanesDTO = rentableAirplaneService.getRentableAirplanes();
        return ResponseEntity.ok(airplanesDTO);
    }

}
