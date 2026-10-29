package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PilotMapper {

    public PilotResponseDTO toPilotResponseDTO(Pilot pilot){

        if(pilot == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        PilotResponseDTO pilotResponseDTO = new PilotResponseDTO();
        pilotResponseDTO.setAssociated(pilot.isAssociated());

        return pilotResponseDTO;
    }



}
