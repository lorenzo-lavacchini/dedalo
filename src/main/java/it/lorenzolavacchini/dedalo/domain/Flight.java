package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate flightDate;

    @Column(nullable = false, length = 50)
    private String departureAirfield;

    @Column(nullable = false, length = 50)
    private String arrivalAirfield;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    @Column(nullable = false, length = 50)
    private String notes;

    @OneToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    private Airplane airplaneUsed;

}
