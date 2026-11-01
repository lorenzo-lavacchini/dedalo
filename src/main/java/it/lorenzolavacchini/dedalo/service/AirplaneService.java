package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.airplane.*;
import it.lorenzolavacchini.dedalo.exception.AirplaneAlreadyRegisteredException;
import it.lorenzolavacchini.dedalo.exception.AirplaneNotFoundException;
import it.lorenzolavacchini.dedalo.exception.AirplaneTypeNotValidException;
import it.lorenzolavacchini.dedalo.exception.PilotNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.AirplaneMapper;
import it.lorenzolavacchini.dedalo.mapper.PrivateAirplaneMapper;
import it.lorenzolavacchini.dedalo.mapper.RentableAirplaneMapper;
import it.lorenzolavacchini.dedalo.repository.AirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import it.lorenzolavacchini.dedalo.repository.PrivateAirplaneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final PilotRepository pilotRepository;
    private final PrivateAirplaneRepository privateAirplaneRepository;
    private final PrivateAirplaneMapper privateAirplaneMapper;
    private final RentableAirplaneMapper rentableAirplaneMapper;
    private final AirplaneMapper airplaneMapper;

    public AirplaneService(AirplaneRepository airplaneRepository, PilotRepository pilotRepository, PrivateAirplaneRepository privateAirplaneRepository, PrivateAirplaneRepository privateAirplaneRepository1,
                           PrivateAirplaneMapper privateAirplaneMapper, RentableAirplaneMapper rentableAirplaneMapper,
                           AirplaneMapper airplaneMapper) {
        this.airplaneRepository = airplaneRepository;
        this.pilotRepository = pilotRepository;
        this.privateAirplaneRepository = privateAirplaneRepository1;
        this.privateAirplaneMapper = privateAirplaneMapper;
        this.rentableAirplaneMapper = rentableAirplaneMapper;
        this.airplaneMapper = airplaneMapper;
    }

    @Transactional
    public AirplaneResponseDTO createAirplane(AirplaneRequestDTO airplaneRequestDTO) {
        Airplane airplane;

        if(airplaneRepository.existsByRegistrationNumber(airplaneRequestDTO.getRegistrationNumber())){
            throw new AirplaneAlreadyRegisteredException();
        }

        if (airplaneRequestDTO instanceof PrivateAirplaneRequestDTO privateAirplaneRequestDTO) {
            Pilot owner = pilotRepository.findById(privateAirplaneRequestDTO.getOwnerId())
                    .orElseThrow(() -> new PilotNotFoundException());

            airplane = privateAirplaneMapper.toEntity(privateAirplaneRequestDTO, owner);

        } else if (airplaneRequestDTO instanceof RentableAirplaneRequestDTO rentableAirplaneRequestDTO) {
            airplane = rentableAirplaneMapper.toEntity(rentableAirplaneRequestDTO);

        } else {
            throw new IllegalArgumentException("DTO type not valid");
        }

        airplaneRepository.save(airplane);

        AirplaneResponseDTO airplaneResponseDTO = airplaneMapper.toResponseDTO(airplane);
        return airplaneResponseDTO;
    }

    public List<AirplaneResponseDTO> getAll(){

        List<Airplane> airplanes = airplaneRepository.findByDeletedFalse();

        List<AirplaneResponseDTO> airplanesDTO = new ArrayList<>();

        if (airplanes.isEmpty()){
            return airplanesDTO;
        }

        for (Airplane airplane : airplanes){
            airplanesDTO.add(airplaneMapper.toResponseDTO(airplane));
        }

        return airplanesDTO;
    }

    @Transactional
    public void deleteLogic(Long id){

        Airplane airplane = airplaneRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new AirplaneNotFoundException());

        airplane.setDeleted(true);
        airplaneRepository.save(airplane);
    }

    public List<PrivateAirplaneResponseDTO> getAirplanesByPilotId(Long pilotId){

        List<PrivateAirplane> airplanes = privateAirplaneRepository.findByDeletedFalseAndOwner_Id(pilotId);

        List<PrivateAirplaneResponseDTO> airplanesDTO = new ArrayList<>();

        if (airplanes.isEmpty()){
            return airplanesDTO;
        }

        for (PrivateAirplane privateAirplane : airplanes){
            airplanesDTO.add(privateAirplaneMapper.toResponseDTO(privateAirplane));
        }

        return airplanesDTO;
    }
}

