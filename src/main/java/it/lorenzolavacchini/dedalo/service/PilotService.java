package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.domain.Logbook;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.repository.PilotRepository;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository){
        this.pilotRepository = pilotRepository;
    }

    public Pilot create(User user, AbilitationProfileRequestDTO abilitationProfileRequestDTO){




        Pilot pilotRole = new Pilot(user);
        Logbook logbook = new Logbook();
        AbilitationProfile abilitationProfile = new AbilitationProfile();

        logbook.setOwner(pilotRole);
        abilitationProfile.setOwner(pilotRole);

        pilotRole.setLogbook(logbook);
        pilotRole.setAbilitationProfile(abilitationProfile);

        user.setPilotRole(pilotRole);





        return pilotRole;

    }

}
