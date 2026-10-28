package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Logbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Pilot owner;

    @OneToMany(mappedBy = "logbook", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Flight> flights = new ArrayList<>();

    public Logbook(){

    }

    public Logbook(Pilot owner){
        this.creationDate = LocalDate.now();
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Pilot getOwner() {
        return owner;
    }

    public void setOwner(Pilot owner) {
        this.owner = owner;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
