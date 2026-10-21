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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentable_airplane_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private RentableAirplane rentedAirplane;

    @ManyToOne
    @JoinColumn(name = "renter_pilot_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Pilot renterPilot;

    public Rental(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }

    public RentalState getRentalState() {
        return rentalState;
    }

    public void setRentalState(RentalState rentalState) {
        this.rentalState = rentalState;
    }

    public RentableAirplane getRentedAirplane() {
        return rentedAirplane;
    }

    public void setRentedAirplane(RentableAirplane rentedAirplane) {
        this.rentedAirplane = rentedAirplane;
    }

    public Pilot getRenterPilot() {
        return renterPilot;
    }

    public void setRenterPilot(Pilot renterPilot) {
        this.renterPilot = renterPilot;
    }
}
