package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.hibernate.mapping.Join;

import java.time.LocalTime;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate bookingDate;

    private LocalTime bookingTime;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    @Column(nullable = false)
    private int totalRate;

    @Enumerated(EnumType.STRING)
    private RentalState rentalState;

    @ManyToOne
    @JoinColumn(name = "rentable_airplane_id", nullable = false)
    private RentableAirplane rentedAirplane;

    @ManyToOne
    @JoinColumn(name = "pilot_id", nullable = false)
    private Pilot renterPilot;

}
