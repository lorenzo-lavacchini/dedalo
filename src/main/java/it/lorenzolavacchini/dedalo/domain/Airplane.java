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

    public Airplane(){

    }

}
