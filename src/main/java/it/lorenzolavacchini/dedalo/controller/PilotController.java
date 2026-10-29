package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.service.PilotService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PatchMapping("/association/{id}")
    public ResponseEntity<PilotResponseDTO> makeAssociated(@PathVariable Long id){
        pilotService.makeAssociated(id);
        PilotResponseDTO pilotResponseDTO = pilotService.getPilotResponseDTOById(id);
        return ResponseEntity.ok(pilotResponseDTO);
    }

}
