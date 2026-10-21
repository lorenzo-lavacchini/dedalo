package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime bookedAt;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private int totalRate;

    @Enumerated(EnumType.STRING)
    private RentalState rentalState;

    @ManyToOne
    @JoinColumn(name = "rentable_airplane_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private RentableAirplane rentedAirplane;

    @ManyToOne
    @JoinColumn(name = "renter_pilot_id")
    private Pilot renterPilot;

    public Rental(){

    }

}
