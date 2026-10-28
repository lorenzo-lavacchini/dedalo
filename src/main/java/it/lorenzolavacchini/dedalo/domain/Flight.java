package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logbook_id", nullable = false)
    private Logbook logbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_used_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Airplane airplaneUsed;

    public Flight(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Logbook getLogbook() {
        return logbook;
    }

    public void setLogbook(Logbook logbook) {
        this.logbook = logbook;
    }

    public Airplane getAirplaneUsed() {
        return airplaneUsed;
    }

    public void setAirplaneUsed(Airplane airplaneUsed) {
        this.airplaneUsed = airplaneUsed;
    }
}
