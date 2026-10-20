package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class RentableAirplane extends Airplane {

    @Column(nullable = false)
    private int hourlyRate;

    public RentableAirplane(){
        super();
    }

}
