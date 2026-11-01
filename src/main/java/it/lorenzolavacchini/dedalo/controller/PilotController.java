package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileResponseDTO;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.service.PilotService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    //FORSE SI PUò TOGLIERE PERCHE' SI PUO' FARE CHE UN PILOTA DIVENTA SOCIO ESATTAMENTE QUANDO VIENE
    //SALDATO IL PAGAMENTO A LUI RELATIVO DELLA QUOTA DI ASSOCIAZIONE DELL'ANNO SOLARE IN CORSO!
    //IN QUESTO MODO SI HA UNA SINGOLA FONTE DI " VERITA' "
    @PatchMapping("/users/{userId}/pilot/association")
    public ResponseEntity<PilotResponseDTO> makeAssociated(@PathVariable Long userId){
        PilotResponseDTO pilotResponseDTO = pilotService.makeAssociated(userId);
        return ResponseEntity.ok(pilotResponseDTO);
    }

    @GetMapping("/users/{userId}/pilot/abilitation-profile")
    public ResponseEntity<AbilitationProfileResponseDTO> getAbilitations(@PathVariable Long userId){
        AbilitationProfileResponseDTO abilitationProfileResponseDTO = pilotService.getAbilitations(userId);
        return ResponseEntity.ok(abilitationProfileResponseDTO);
    }

    @PatchMapping("users/{userId}/pilot/abilitation-profile")
    public ResponseEntity<AbilitationProfileResponseDTO> updateAbilitations(@PathVariable Long userId,
                                                                            @Valid @RequestBody AbilitationProfileRequestDTO abilitationProfileRequestDTO){

        AbilitationProfileResponseDTO abilitationProfileResponseDTO = pilotService.updateAbilitations(userId, abilitationProfileRequestDTO);
        return ResponseEntity.ok(abilitationProfileResponseDTO);
    }

}
