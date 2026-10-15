package it.lorenzolavacchini.dedalo.domain;

import java.time.LocalDate;

public class PPR {

    private Long id;

    private PPRState PPRState;

    private LocalDate creationDate;

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    private String pilotName;

    private String pilotSurname;

    private String pilotTelephone;

    private String pilotEmail;

    private String airplaneRegistrationNumber;

    private int MTOW;

    private StaffMember handler;

    private User creator;


}
