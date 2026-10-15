package it.lorenzolavacchini.dedalo.domain;

import java.time.LocalTime;
import java.time.LocalDate;

public class Rental {

    private LocalDate bookingDate;

    private LocalTime bookingTime;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int totalRate;

    private RentalState rentalState;

    private RentableAirplane airplaneRented;
}
