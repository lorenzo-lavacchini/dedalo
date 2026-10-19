package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn
public class PrivateAirplane extends Airplane{

    private Pilot owner;

}
