package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("RentalPayment")
public class RentalPayment extends Payment{

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    public RentalPayment(){
        super();
    }
}
