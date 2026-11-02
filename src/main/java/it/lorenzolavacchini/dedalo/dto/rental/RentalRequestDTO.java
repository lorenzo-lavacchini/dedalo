package it.lorenzolavacchini.dedalo.dto.rental;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class RentalRequestDTO {

    @NotNull(message = "L'ID del pilota proprietario è obbligatorio")
    private Long pilotId;

    @NotNull(message = "L'ID dell'aeroplano da noleggiare è obbligatorio")
    private Long airplaneId;

    @NotNull(message = "L'orario di inizio noleggio è obbligatorio")
    private LocalTime startTime;

    @NotNull(message = "L'orario di fine noleggio è obbligatorio")
    private LocalTime endTime;

    @NotNull(message = "La data noleggio è obbligatoria")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public Long getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Long airplaneId) {
        this.airplaneId = airplaneId;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
