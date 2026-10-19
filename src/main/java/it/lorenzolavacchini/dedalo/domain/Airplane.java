package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 6)
    private String airplaneRegistrationNumber;

    @Column(nullable = false)
    private boolean advanced;

    @Column(nullable = false, length = 30)
    private String modelName;

}
