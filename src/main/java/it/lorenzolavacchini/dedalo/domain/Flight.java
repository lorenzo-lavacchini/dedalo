package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate flightDate;

    @Column(nullable = false, length = 50)
    private String departureAirfield;

    @Column(nullable = false, length = 50)
    private String arrivalAirfield;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private LocalTime arrivalTime;

    @Column(nullable = false, length = 50)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "airplane_used_id", nullable = false)
    private Airplane airplaneUsed;

    public Flight(){

    }

}
