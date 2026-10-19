package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private boolean associated;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "owner")
    private Logbook logbook;

    @OneToOne(mappedBy = "owner")
    private AbilitationProfile abilitationProfile;

    @OneToMany(mappedBy = "owner")
    private List<PrivateAirplane> ownedAirplanes;
}
