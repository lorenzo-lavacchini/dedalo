package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AbilitationProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licenceNumber;

    @Column(nullable = false)
    private LocalDate licenceExpirationDate;

    @Column(nullable = false)
    private boolean twoSeatsAbilitation;

    @Column(nullable = false)
    private boolean radioAbilitation;

    @Column(nullable = false)
    private boolean advancedAbilitation;

    @OneToOne
    @JoinColumn(name = "owner _id", nullable = false)
    private Pilot owner;

    public AbilitationProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Pilot getOwner() {
        return owner;
    }

    public void setOwner(Pilot owner) {
        this.owner = owner;
    }
}
