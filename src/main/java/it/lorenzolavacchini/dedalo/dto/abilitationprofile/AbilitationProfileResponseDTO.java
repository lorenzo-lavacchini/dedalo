package it.lorenzolavacchini.dedalo.dto.abilitationprofile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import jakarta.persistence.*;

import java.time.LocalDate;

@JsonPropertyOrder({"licenceNumber","licenceExpiration","twoSeatsAbilitation","radioAbilitation","advancedAbilitation"})
public class AbilitationProfileResponseDTO {

    private String licenceNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate licenceExpirationDate;

    private boolean twoSeatsAbilitation;

    private boolean radioAbilitation;

    private boolean advancedAbilitation;

    public AbilitationProfileResponseDTO() {
    }

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
