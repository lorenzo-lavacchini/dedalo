package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean associated;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Logbook logbook;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private AbilitationProfile abilitationProfile;

    @OneToMany(mappedBy = "owner")
    private List<PrivateAirplane> ownedAirplanes;

    public Pilot(){

    }
}
