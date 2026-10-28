package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.exception.NotAllowedAdvancedAbilitationException;
import it.lorenzolavacchini.dedalo.exception.NotAllowedRadioAbilitationException;
import org.springframework.stereotype.Service;

@Service
public class AbilitationProfileService {

    public void validateAbilitations(AbilitationProfile abilitationProfile){
       if(abilitationProfile.isRadioAbilitation()){
           if(!abilitationProfile.isTwoSeatsAbilitation()){
               throw new NotAllowedRadioAbilitationException();
           }
       }

       if(abilitationProfile.isAdvancedAbilitation()){
           if(!abilitationProfile.isRadioAbilitation()){
               throw new NotAllowedAdvancedAbilitationException();
           }
       }
    }
}
