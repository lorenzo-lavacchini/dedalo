package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.PrivateAirplane;
import it.lorenzolavacchini.dedalo.dto.airplane.PrivateAirplaneRequestDTO;
import it.lorenzolavacchini.dedalo.dto.airplane.PrivateAirplaneResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PrivateAirplaneMapper {

    public PrivateAirplane toEntity(PrivateAirplaneRequestDTO privateAirplaneRequestDTO, Pilot owner) {
        PrivateAirplane privateAirplane = new PrivateAirplane();

        privateAirplane.setRegistrationNumber(privateAirplaneRequestDTO.getRegistrationNumber());
        privateAirplane.setAdvanced(privateAirplaneRequestDTO.isAdvanced());
        privateAirplane.setModelName(privateAirplaneRequestDTO.getModelName());

        privateAirplane.setOwner(owner);
        return privateAirplane;
    }

    public PrivateAirplaneResponseDTO toResponseDTO(PrivateAirplane privateAirplane) {
        PrivateAirplaneResponseDTO privateAirplaneResponseDTO = new PrivateAirplaneResponseDTO();

        privateAirplaneResponseDTO.setId(privateAirplane.getId());
        privateAirplaneResponseDTO.setRegistrationNumber(privateAirplane.getRegistrationNumber());
        privateAirplaneResponseDTO.setAdvanced(privateAirplane.isAdvanced());
        privateAirplaneResponseDTO.setModelName(privateAirplane.getModelName());
        privateAirplaneResponseDTO.setType("PRIVATE");

        if (privateAirplane.getOwner() != null) {
            privateAirplaneResponseDTO.setOwnerId(privateAirplane.getOwner().getId());
        }

        return privateAirplaneResponseDTO;
    }
}
