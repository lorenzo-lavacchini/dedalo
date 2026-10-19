package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Logbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot owner;

}
