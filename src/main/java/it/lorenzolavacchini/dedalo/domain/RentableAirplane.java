package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@PrimaryKeyJoinColumn
@OnDelete(action = OnDeleteAction.CASCADE) //se viene eliminato un Airplane astratto, il corrispondente RentableAirplane viene anch'esso eliminato
public class RentableAirplane extends Airplane {

    @Column(nullable = false)
    private int hourlyRate;

    public RentableAirplane(){
        super();
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
