package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.RentableAirplane;
import it.lorenzolavacchini.dedalo.dto.airplane.RentableAirplaneResponseDTO;
import it.lorenzolavacchini.dedalo.mapper.RentableAirplaneMapper;
import it.lorenzolavacchini.dedalo.repository.RentableAirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentableAirplaneService {

    private final RentableAirplaneRepository rentableAirplaneRepository;
    private final RentableAirplaneMapper rentableAirplaneMapper;

    public RentableAirplaneService(RentableAirplaneRepository rentableAirplaneRepository, RentableAirplaneMapper rentableAirplaneMapper) {
        this.rentableAirplaneRepository = rentableAirplaneRepository;
        this.rentableAirplaneMapper = rentableAirplaneMapper;
    }

    public boolean exists(Long id){
        return rentableAirplaneRepository.existsById(id);
    }

    public List<RentableAirplaneResponseDTO> getRentableAirplanes(){
        List<RentableAirplane> airplanes = rentableAirplaneRepository.findAll();

        List<RentableAirplaneResponseDTO> airplanesDTO = new ArrayList<>();

        if (airplanes.isEmpty()){
            return airplanesDTO;
        }

        for(RentableAirplane rentableAirplane : airplanes){
            airplanesDTO.add(rentableAirplaneMapper.toResponseDTO(rentableAirplane));
        }

        return airplanesDTO;
    }

}
