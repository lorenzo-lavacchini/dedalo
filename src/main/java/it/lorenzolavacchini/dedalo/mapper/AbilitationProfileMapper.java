package it.lorenzolavacchini.dedalo.mapper;


import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class AbilitationProfileMapper {

    public AbilitationProfile toEntity(AbilitationProfileRequestDTO abilitationProfileRequestDTO){
        if(abilitationProfileRequestDTO == null){
            throw new IllegalArgumentException("Can't map a null object to an entity");
        }

        AbilitationProfile abilitationProfile = new AbilitationProfile();

        abilitationProfile.setLicenceNumber(abilitationProfileRequestDTO.getLicenceNumber());
        abilitationProfile.setLicenceExpirationDate(abilitationProfileRequestDTO.getLicenceExpirationDate());
        abilitationProfile.setTwoSeatsAbilitation(abilitationProfileRequestDTO.isTwoSeatsAbilitation());
        abilitationProfile.setRadioAbilitation(abilitationProfileRequestDTO.isRadioAbilitation());
        abilitationProfile.setAdvancedAbilitation(abilitationProfileRequestDTO.isAdvancedAbilitation());

        return abilitationProfile;
    }


}
