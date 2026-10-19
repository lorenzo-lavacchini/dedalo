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

    private LocalDate licenceExpirationDate;

    @Column(nullable = false)
    private boolean twoSeatsAbilitation;

    @Column(nullable = false)
    private boolean radioAbilitation;

    @Column(nullable = false)
    private boolean advancedAbilitation;

    @OneToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot owner;
}
