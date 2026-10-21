package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    private List<Flight> flights;

    public Logbook(){

    }

}
