package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.airplane.*;
import it.lorenzolavacchini.dedalo.exception.AirplaneAlreadyRegisteredException;
import it.lorenzolavacchini.dedalo.exception.AirplaneTypeNotValidException;
import it.lorenzolavacchini.dedalo.exception.PilotNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.AirplaneMapper;
import it.lorenzolavacchini.dedalo.mapper.PrivateAirplaneMapper;
import it.lorenzolavacchini.dedalo.mapper.RentableAirplaneMapper;
import it.lorenzolavacchini.dedalo.repository.AirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final PilotRepository pilotRepository;
    private final PrivateAirplaneMapper privateAirplaneMapper;
    private final RentableAirplaneMapper rentableAirplaneMapper;
    private final AirplaneMapper airplaneMapper;

    public AirplaneService(AirplaneRepository airplaneRepository, PilotRepository pilotRepository, PrivateAirplaneMapper privateAirplaneMapper, RentableAirplaneMapper rentableAirplaneMapper, AirplaneMapper airplaneMapper) {
        this.airplaneRepository = airplaneRepository;
        this.pilotRepository = pilotRepository;
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
}

