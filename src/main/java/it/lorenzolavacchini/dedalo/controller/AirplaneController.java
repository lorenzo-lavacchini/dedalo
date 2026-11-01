package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import it.lorenzolavacchini.dedalo.dto.airplane.AirplaneRequestDTO;
import it.lorenzolavacchini.dedalo.dto.airplane.AirplaneResponseDTO;
import it.lorenzolavacchini.dedalo.dto.airplane.PrivateAirplaneResponseDTO;
import it.lorenzolavacchini.dedalo.service.AirplaneService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @PostMapping("/airplanes")
    public ResponseEntity<AirplaneResponseDTO> create(@Valid @RequestBody AirplaneRequestDTO airplaneRequestDTO){
        AirplaneResponseDTO airplaneResponseDTO = airplaneService.createAirplane(airplaneRequestDTO);
        return ResponseEntity.ok(airplaneResponseDTO);
    }


    @GetMapping("/airplanes/{id}")
    public ResponseEntity<AirplaneResponseDTO> getById(@PathVariable Long id){
        AirplaneResponseDTO airplaneResponseDTO = airplaneService.getById(id);
        return ResponseEntity.ok(airplaneResponseDTO);
    }


    @GetMapping("/airplanes")
    public ResponseEntity<List<AirplaneResponseDTO>> getAll(){
        List<AirplaneResponseDTO> airplanesResponseDTO = airplaneService.getAll();
        return ResponseEntity.ok(airplanesResponseDTO);
    }

    @DeleteMapping("/airplanes/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        airplaneService.deleteLogic(id);
        return ResponseEntity.ok("Resource correctly deleted");
    }

    @GetMapping("/pilots/{pilotId}/airplanes")
    public ResponseEntity<List<PrivateAirplaneResponseDTO>> getAirplanesByPilotId(@PathVariable Long pilotId){
        List<PrivateAirplaneResponseDTO> airplanesDTO = airplaneService.getAirplanesByPilotId(pilotId);
        return ResponseEntity.ok(airplanesDTO);
    }
}
