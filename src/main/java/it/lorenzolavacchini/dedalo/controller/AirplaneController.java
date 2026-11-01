package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import it.lorenzolavacchini.dedalo.dto.airplane.AirplaneRequestDTO;
import it.lorenzolavacchini.dedalo.service.AirplaneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping("/airplanes")
    public ResponseEntity<Airplane> create(@Valid @RequestBody AirplaneRequestDTO airplaneRequestDTO){
        Airplane airplane = airplaneService.createAirplane(airplaneRequestDTO);
        return ResponseEntity.ok(airplane);
    }



}
