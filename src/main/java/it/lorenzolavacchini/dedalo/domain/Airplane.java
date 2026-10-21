package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
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
