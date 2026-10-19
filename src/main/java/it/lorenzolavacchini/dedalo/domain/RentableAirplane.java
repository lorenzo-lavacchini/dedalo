package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class RentableAirplane extends Airplane {

    private int hourlyRate;

}
