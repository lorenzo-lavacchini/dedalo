package it.lorenzolavacchini.dedalo.domain;

import java.time.LocalDate;
import java.util.List;

public class Logbook {

    private Long id;

    private LocalDate creationDate;

    private List<Flight> flights; //implementare con inizializzazione Lazy. Altrimenti togliere del tutto

}
