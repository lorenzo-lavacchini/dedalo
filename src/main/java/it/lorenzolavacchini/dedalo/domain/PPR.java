package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PPR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PPRState PPRState;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDate arrivalDate;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false, length = 50)
    private String pilotName;

    @Column(nullable = false, length = 50)
    private String pilotSurname;

    @Column(nullable = false, length = 50)
    private String pilotTelephone;

    @Column(nullable = false, length = 50)
    private String pilotEmail;

    @Column(nullable = false, length = 50)
    private String airplaneRegistrationNumber;

    @Column(nullable = false)
    private int MTOW;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handler_id")
    private StaffMember handler;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    public PPR(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PPRState getPPRState() {
        return PPRState;
    }

    public void setPPRState(PPRState PPRState) {
        this.PPRState = PPRState;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getPilotSurname() {
        return pilotSurname;
    }

    public void setPilotSurname(String pilotSurname) {
        this.pilotSurname = pilotSurname;
    }

    public String getPilotTelephone() {
        return pilotTelephone;
    }

    public void setPilotTelephone(String pilotTelephone) {
        this.pilotTelephone = pilotTelephone;
    }

    public String getPilotEmail() {
        return pilotEmail;
    }

    public void setPilotEmail(String pilotEmail) {
        this.pilotEmail = pilotEmail;
    }

    public String getAirplaneRegistrationNumber() {
        return airplaneRegistrationNumber;
    }

    public void setAirplaneRegistrationNumber(String airplaneRegistrationNumber) {
        this.airplaneRegistrationNumber = airplaneRegistrationNumber;
    }

    public int getMTOW() {
        return MTOW;
    }

    public void setMTOW(int MTOW) {
        this.MTOW = MTOW;
    }

    public StaffMember getHandler() {
        return handler;
    }

    public void setHandler(StaffMember handler) {
        this.handler = handler;
    }
}
