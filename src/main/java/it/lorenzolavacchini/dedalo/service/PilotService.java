package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.*;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileResponseDTO;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import it.lorenzolavacchini.dedalo.exception.PilotAlreadyAssociatedException;
import it.lorenzolavacchini.dedalo.exception.PilotNotFoundException;
import it.lorenzolavacchini.dedalo.exception.UserIsNotPilotException;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.AbilitationProfileMapper;
import it.lorenzolavacchini.dedalo.mapper.PilotMapper;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;
    private final AbilitationProfileService abilitationProfileService;
    private final AbilitationProfileMapper abilitationProfileMapper;
    private final PilotMapper pilotMapper;
    private final UserRepository userRepository;

    public PilotService(PilotRepository pilotRepository, AbilitationProfileService abilitationProfileService, AbilitationProfileMapper abilitationProfileMapper, PilotMapper pilotMapper, UserRepository userRepository){
        this.pilotRepository = pilotRepository;
        this.abilitationProfileService = abilitationProfileService;
        this.abilitationProfileMapper = abilitationProfileMapper;
        this.pilotMapper = pilotMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public Pilot create(User user, AbilitationProfileRequestDTO abilitationProfileRequestDTO){

        Pilot pilotRole = new Pilot(user);
        Logbook logbook = new Logbook(pilotRole);

        AbilitationProfile abilitationProfile = abilitationProfileMapper.toEntity(abilitationProfileRequestDTO);
        abilitationProfileService.validateAbilitations(abilitationProfile);

        logbook.setOwner(pilotRole);
        abilitationProfile.setOwner(pilotRole);

        pilotRole.setLogbook(logbook);
        pilotRole.setAbilitationProfile(abilitationProfile);

        user.setPilotRole(pilotRole);
        return pilotRole;
    }

    @Transactional
    public PilotResponseDTO makeAssociated(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Pilot pilot = user.getPilotRole();
        if(pilot.isAssociated()){
            throw new PilotAlreadyAssociatedException();
        }

        pilot.setAssociated(true);
        PilotResponseDTO pilotResponseDTO = pilotMapper.toPilotResponseDTO(pilot);
        pilotRepository.save(pilot);
        return pilotResponseDTO;
    }

    public PilotResponseDTO getPilotResponseDTOById(Long id){

        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(() -> new PilotNotFoundException());

        PilotResponseDTO pilotResponseDTO = pilotMapper.toPilotResponseDTO(pilot);

        return pilotResponseDTO;
    }

    public Pilot getById(Long id){

        Pilot pilot = pilotRepository.findById(id)
                .orElseThrow(() -> new PilotNotFoundException());

        return pilot;
    }

    public AbilitationProfileResponseDTO getAbilitations(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        AbilitationProfile abilitationProfile = user.getPilotRole().getAbilitationProfile();

        AbilitationProfileResponseDTO abilitationProfileResponseDTO =
                abilitationProfileMapper.toAbilitationProfileResponseDTO(abilitationProfile);

        return abilitationProfileResponseDTO;
    }

    @Transactional
    public AbilitationProfileResponseDTO updateAbilitations(Long userId, AbilitationProfileRequestDTO abilitationProfileRequestDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        if(user.getPilotRole() == null){
            throw new UserIsNotPilotException();
        }

        AbilitationProfile abilitationProfile = user.getPilotRole().getAbilitationProfile();

        abilitationProfile.setLicenceNumber(abilitationProfileRequestDTO.getLicenceNumber());
        abilitationProfile.setLicenceExpirationDate(abilitationProfileRequestDTO.getLicenceExpirationDate());
        abilitationProfile.setTwoSeatsAbilitation(abilitationProfileRequestDTO.isTwoSeatsAbilitation());
        abilitationProfile.setRadioAbilitation(abilitationProfileRequestDTO.isRadioAbilitation());
        abilitationProfile.setAdvancedAbilitation(abilitationProfileRequestDTO.isAdvancedAbilitation());

        AbilitationProfileResponseDTO abilitationProfileResponseDTO =
                abilitationProfileMapper.toAbilitationProfileResponseDTO(abilitationProfile);

        userRepository.save(user);
        return abilitationProfileResponseDTO;
    }

    public boolean canPilotAirplane(Airplane airplane, Pilot pilot){

        if (airplane.isAdvanced() && !pilot.getAbilitationProfile().isAdvancedAbilitation()){
            return false;
        }

        return true;

    }





}

