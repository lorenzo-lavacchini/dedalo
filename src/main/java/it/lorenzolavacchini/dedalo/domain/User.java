package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String surname;

    @Column(nullable = false, length = 50)
    private String telephone;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private StaffMember staffMemberRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pilot pilotRole;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Admin adminRole;

    public User(){

    }

    public User(String name, String surname, String telephone, String email, String password){
        this.setName(name);
        this.setSurname(surname);
        this.setTelephone(telephone);
        this.setEmail(email);
        this.setPassword(password);
        this.setStaffMemberRole(null);
        this.setPilotRole(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffMember getStaffMemberRole() {
        return staffMemberRole;
    }

    public void setStaffMemberRole(StaffMember staffMemberRole) {
        this.staffMemberRole = staffMemberRole;
    }

    public Pilot getPilotRole() {
        return pilotRole;
    }

    public void setPilotRole(Pilot pilotRole) {
        this.pilotRole = pilotRole;
    }
}
