package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class AbilitationProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
