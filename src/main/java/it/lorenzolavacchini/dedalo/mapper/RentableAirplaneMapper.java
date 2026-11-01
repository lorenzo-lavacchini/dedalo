package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.RentableAirplane;
import it.lorenzolavacchini.dedalo.dto.airplane.RentableAirplaneRequestDTO;
import it.lorenzolavacchini.dedalo.dto.airplane.RentableAirplaneResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RentableAirplaneMapper {

    public RentableAirplane toEntity(RentableAirplaneRequestDTO rentableAirplaneRequestDTO) {
        RentableAirplane airplane = new RentableAirplane();

        airplane.setRegistrationNumber(rentableAirplaneRequestDTO.getRegistrationNumber());
        airplane.setAdvanced(rentableAirplaneRequestDTO.isAdvanced());
        airplane.setModelName(rentableAirplaneRequestDTO.getModelName());

        airplane.setHourlyRate(rentableAirplaneRequestDTO.getHourlyRate());
        return airplane;
    }

    public RentableAirplaneResponseDTO toResponseDTO(RentableAirplane rentableAirplane) {
        RentableAirplaneResponseDTO dto = new RentableAirplaneResponseDTO();

        dto.setId(rentableAirplane.getId());
        dto.setRegistrationNumber(rentableAirplane.getRegistrationNumber());
        dto.setAdvanced(rentableAirplane.isAdvanced());
        dto.setModelName(rentableAirplane.getModelName());
        dto.setType("RENTABLE");

        dto.setHourlyRate(rentableAirplane.getHourlyRate());
        return dto;
    }
}
