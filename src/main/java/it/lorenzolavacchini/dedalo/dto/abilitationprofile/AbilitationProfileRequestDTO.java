package it.lorenzolavacchini.dedalo.dto.abilitationprofile;

import java.time.LocalDate;

public class AbilitationProfileRequestDTO {

    private String licenceNumber;

    private LocalDate licenceExpirationDate;

    private boolean twoSeatsAbilitation;

    private boolean radioAbilitation;

    private boolean advancedAbilitation;

    public AbilitationProfileRequestDTO(){}

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public LocalDate getLicenceExpirationDate() {
        return licenceExpirationDate;
    }

    public void setLicenceExpirationDate(LocalDate licenceExpirationDate) {
        this.licenceExpirationDate = licenceExpirationDate;
    }

    public boolean isTwoSeatsAbilitation() {
        return twoSeatsAbilitation;
    }

    public void setTwoSeatsAbilitation(boolean twoSeatsAbilitation) {
        this.twoSeatsAbilitation = twoSeatsAbilitation;
    }

    public boolean isRadioAbilitation() {
        return radioAbilitation;
    }

    public void setRadioAbilitation(boolean radioAbilitation) {
        this.radioAbilitation = radioAbilitation;
    }

    public boolean isAdvancedAbilitation() {
        return advancedAbilitation;
    }

    public void setAdvancedAbilitation(boolean advancedAbilitation) {
        this.advancedAbilitation = advancedAbilitation;
    }
}
