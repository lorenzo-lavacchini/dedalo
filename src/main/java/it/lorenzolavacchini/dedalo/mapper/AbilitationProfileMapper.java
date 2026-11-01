package it.lorenzolavacchini.dedalo.mapper;


import it.lorenzolavacchini.dedalo.domain.AbilitationProfile;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileResponseDTO;
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

    public AbilitationProfileResponseDTO toAbilitationProfileResponseDTO(AbilitationProfile abilitationProfile){
        if(abilitationProfile == null){
            throw new IllegalArgumentException("Can't map a null object to a DTO");
        }

        AbilitationProfileResponseDTO abilitationProfileResponseDTO = new AbilitationProfileResponseDTO();

        abilitationProfileResponseDTO.setLicenceNumber(abilitationProfile.getLicenceNumber());
        abilitationProfileResponseDTO.setLicenceExpirationDate(abilitationProfile.getLicenceExpirationDate());
        abilitationProfileResponseDTO.setTwoSeatsAbilitation(abilitationProfile.isTwoSeatsAbilitation());
        abilitationProfileResponseDTO.setRadioAbilitation(abilitationProfile.isRadioAbilitation());
        abilitationProfileResponseDTO.setAdvancedAbilitation(abilitationProfile.isAdvancedAbilitation());

        return abilitationProfileResponseDTO;
    }
}
