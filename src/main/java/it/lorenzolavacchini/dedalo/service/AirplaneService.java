package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.airplane.*;
import it.lorenzolavacchini.dedalo.exception.AirplaneAlreadyRegisteredException;
import it.lorenzolavacchini.dedalo.exception.AirplaneTypeNotValidException;
import it.lorenzolavacchini.dedalo.exception.PilotNotFoundException;
import it.lorenzolavacchini.dedalo.repository.AirplaneRepository;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;
    private final PilotRepository pilotRepository;

    public AirplaneService(AirplaneRepository airplaneRepository, PilotRepository pilotRepository) {
        this.airplaneRepository = airplaneRepository;
        this.pilotRepository = pilotRepository;
    }

    @Transactional
    public Airplane createAirplane(AirplaneRequestDTO airplaneRequestDTO) {
        Airplane airplane;

        if(airplaneRepository.existsByRegistrationNumber(airplaneRequestDTO.getRegistrationNumber())){
            throw new AirplaneAlreadyRegisteredException();
        }

        if (airplaneRequestDTO instanceof PrivateAirplaneRequestDTO privateDto) {
            PrivateAirplane privateAirplane = new PrivateAirplane();

            Pilot owner = pilotRepository.findById(privateDto.getOwnerId())
                    .orElseThrow(() -> new PilotNotFoundException());

            privateAirplane.setOwner(owner);
            airplane = privateAirplane;

        } else if (airplaneRequestDTO instanceof RentableAirplaneRequestDTO rentableDto) {
            RentableAirplane rentableAirplane = new RentableAirplane();
            rentableAirplane.setHourlyRate(rentableDto.getHourlyRate());
            airplane = rentableAirplane;

        } else {
            throw new AirplaneTypeNotValidException();
        }

        //Mappatura dei campi comuni della classe astratta Airplane
        airplane.setRegistrationNumber(airplaneRequestDTO.getRegistrationNumber());
        airplane.setAdvanced(airplaneRequestDTO.isAdvanced());
        airplane.setModelName(airplaneRequestDTO.getModelName());

        //Hibernate salva automaticamente sia nella tabella base Airplane
        //sia nella tabella della ripsettiva sottoclasse grazie alla strategia JOINED
        airplaneRepository.save(airplane);
        return airplane;
    }

    /*@Transactional
    public AirplaneResponseDTO createAirplane(AirplaneRequestDTO airplaneRequestDTO) {
        Airplane airplane;
        AirplaneResponseDTO airplaneResponseDTO;

        if(airplaneRepository.existsByRegistrationNumber(airplaneRequestDTO.getRegistrationNumber())){
            throw new AirplaneAlreadyRegisteredException();
        }

        if (airplaneRequestDTO instanceof PrivateAirplaneRequestDTO privateDto) {
            PrivateAirplane privateAirplane = new PrivateAirplane();

            Pilot owner = pilotRepository.findById(privateDto.getOwnerId())
                    .orElseThrow(() -> new PilotNotFoundException());

            privateAirplane.setOwner(owner);

            airplaneResponseDTO = new PrivateAirplaneResponseDTO();
            airplaneResponseDTO.setId(privateAirplane.getId());
            airplaneResponseDTO.setRegistrationNumber(privateAirplane.getRegistrationNumber());
            airplaneResponseDTO.setAdvanced(privateAirplane.isAdvanced());
            airplaneResponseDTO.setModelName(privateAirplane.getModelName());
            airplaneResponseDTO.setType("PRIVATE");
            ((PrivateAirplaneResponseDTO) airplaneResponseDTO).setOwnerId(owner.getId());

            airplane = privateAirplane;

        } else if (airplaneRequestDTO instanceof RentableAirplaneRequestDTO rentableDto) {
            RentableAirplane rentableAirplane = new RentableAirplane();
            rentableAirplane.setHourlyRate(rentableDto.getHourlyRate());

            airplaneResponseDTO = new RentableAirplaneResponseDTO();
            airplaneResponseDTO.setId(rentableAirplane.getId());
            airplaneResponseDTO.setRegistrationNumber(rentableAirplane.getRegistrationNumber());
            airplaneResponseDTO.setAdvanced(rentableAirplane.isAdvanced());
            airplaneResponseDTO.setModelName(rentableAirplane.getModelName());
            airplaneResponseDTO.setType("RENTABLE");
            ((RentableAirplaneResponseDTO) airplaneResponseDTO).setHourlyRate(rentableAirplane.getHourlyRate());

            airplane = rentableAirplane;

        } else {
            throw new AirplaneTypeNotValidException();
        }

        //Mappatura dei campi comuni della classe astratta Airplane
        airplane.setRegistrationNumber(airplaneRequestDTO.getRegistrationNumber());
        airplane.setAdvanced(airplaneRequestDTO.isAdvanced());
        airplane.setModelName(airplaneRequestDTO.getModelName());

        //Hibernate salva automaticamente sia nella tabella base Airplane
        //sia nella tabella della ripsettiva sottoclasse grazie alla strategia JOINED
        airplaneRepository.save(airplane);
        return airplaneResponseDTO;
    }*/

}

