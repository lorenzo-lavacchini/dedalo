package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToOne(mappedBy = "user")
    private StaffMember staffMemberRole;

    @OneToOne(mappedBy = "user")
    private Pilot pilotRole;

    public User(){

    }
}
