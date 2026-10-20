package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn
public class PrivateAirplane extends Airplane{

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Pilot owner;

    public PrivateAirplane(){
        super();
    }

}
