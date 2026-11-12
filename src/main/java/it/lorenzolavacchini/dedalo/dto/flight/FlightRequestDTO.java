package it.lorenzolavacchini.dedalo.dto.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightRequestDTO {

    @NotNull(message = "L'id dell'aeroplano usato è obbligatoria")
    private Long airplaneId;

    @NotNull(message = "La data del volo è obbligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate flightDate;

    @NotNull(message = "L'aeroporto di partenza è obbligatorio")
    @Size(max = 50)
    private String departureAirfield;

    @NotNull(message = "L'aeroporto di arrivo è obbligatorio")
    @Size(max = 50)
    private String arrivalAirfield;

    @NotNull(message = "L'orario di decollo è obbligatorio")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @NotNull(message = "L'orario di atterraggio è obbligatorio")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;

    @Size(max = 50)
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
