package it.lorenzolavacchini.dedalo.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {

    private LocalDate flightDate;

    private String departureAirfield;

    private String arrivalAirfield;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String notes;

    private Logbook logbook;

    private Airplane airplaneUsed;

}
