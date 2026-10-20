package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 3)
    private String staffMemberID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public StaffMember(){

    }

}
