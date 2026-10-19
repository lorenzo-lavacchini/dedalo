package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PPR {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PPRState PPRState;

    private LocalDate creationDate;

    private LocalDate arrivalDate;

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

    @ManyToOne
    @JoinColumn(name = "staff_member_id")
    private StaffMember handler;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

}
