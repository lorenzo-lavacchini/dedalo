package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@PrimaryKeyJoinColumn
@OnDelete(action = OnDeleteAction.CASCADE) //se viene eliminato un Airplane astratto, il corrisppondente PrivateAirplane viene anch'esso eliminato
public class PrivateAirplane extends Airplane{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Pilot owner;

    public PrivateAirplane(){
        super();
    }

    public Pilot getOwner() {
        return owner;
    }

    public void setOwner(Pilot owner) {
        this.owner = owner;
    }
}
