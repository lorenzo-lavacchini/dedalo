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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PrivateAirplane> ownedAirplanes;

    public Pilot(){

    }

    public Pilot(User user){
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
    }

    public AbilitationProfile getAbilitationProfile() {
        return abilitationProfile;
    }

    public void setAbilitationProfile(AbilitationProfile abilitationProfile) {
        this.abilitationProfile = abilitationProfile;
    }

    public List<PrivateAirplane> getOwnedAirplanes() {
        return ownedAirplanes;
    }

    public void setOwnedAirplanes(List<PrivateAirplane> ownedAirplanes) {
        this.ownedAirplanes = ownedAirplanes;
    }
}
