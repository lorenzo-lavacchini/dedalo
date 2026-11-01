package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
//Nota: la creazione di un'istanza di classe concreta PrivateAirplane o RentableAirplane, porta alla creazione automatica anche
//dell'istanza di classe astratta necessaria corrispondente. Fa tutto in automatico hibernate, e non serve inserire annotazioni cascade ecc.
public abstract class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 6)
    private String registrationNumber;

    @Column(nullable = false)
    private boolean advanced;

    @Column(nullable = false, length = 50)
    private String modelName;

    @Column(nullable = false)
    private boolean deleted = false;

    public Airplane(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
