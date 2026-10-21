package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 3)
    private String staffMemberID;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public StaffMember(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffMemberID() {
        return staffMemberID;
    }

    public void setStaffMemberID(String staffMemberID) {
        this.staffMemberID = staffMemberID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
