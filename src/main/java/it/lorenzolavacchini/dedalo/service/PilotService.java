package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.domain.Logbook;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.mapper.AbilitationProfileMapper;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;
    private final AbilitationProfileService abilitationProfileService;
    private final AbilitationProfileMapper abilitationProfileMapper;

    public PilotService(PilotRepository pilotRepository, AbilitationProfileService abilitationProfileService, AbilitationProfileMapper abilitationProfileMapper){
        this.pilotRepository = pilotRepository;
        this.abilitationProfileService = abilitationProfileService;
        this.abilitationProfileMapper = abilitationProfileMapper;
    }

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

}
