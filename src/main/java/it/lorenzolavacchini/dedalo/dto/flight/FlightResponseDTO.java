package it.lorenzolavacchini.dedalo.dto.flight;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightResponseDTO {

    private Long airplaneId;

    private LocalDate flightDate;

    private String departureAirfield;

    private String arrivalAirfield;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String notes;


    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public String getDepartureAirfield() {
        return departureAirfield;
    }

    public void setDepartureAirfield(String departureAirfield) {
        this.departureAirfield = departureAirfield;
    }

    public String getArrivalAirfield() {
        return arrivalAirfield;
    }

    public void setArrivalAirfield(String arrivalAirfield) {
        this.arrivalAirfield = arrivalAirfield;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
    }
}
