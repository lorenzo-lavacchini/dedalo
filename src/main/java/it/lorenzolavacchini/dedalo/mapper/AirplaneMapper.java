package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import it.lorenzolavacchini.dedalo.domain.PrivateAirplane;
import it.lorenzolavacchini.dedalo.domain.RentableAirplane;
import it.lorenzolavacchini.dedalo.dto.airplane.AirplaneResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {

    private final PrivateAirplaneMapper privateMapper;
    private final RentableAirplaneMapper rentableMapper;

    public AirplaneMapper(PrivateAirplaneMapper privateMapper, RentableAirplaneMapper rentableMapper) {
        this.privateMapper = privateMapper;
        this.rentableMapper = rentableMapper;
    }

    public AirplaneResponseDTO toResponseDTO(Airplane airplane) {
        if (airplane instanceof PrivateAirplane privateEntity) {
            return privateMapper.toResponseDTO(privateEntity);
        } else if (airplane instanceof RentableAirplane rentableEntity) {
            return rentableMapper.toResponseDTO(rentableEntity);
        }
        throw new IllegalArgumentException("Type of airplane entity is unknown");
    }
}
