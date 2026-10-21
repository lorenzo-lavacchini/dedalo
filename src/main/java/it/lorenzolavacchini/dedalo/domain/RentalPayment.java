package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@DiscriminatorValue("RentalPayment")
public class RentalPayment extends Payment{

    @OneToOne
    @JoinColumn(name = "rental_id")
    //non credo ci sia da mettere @OnDelete SET NULL -> perché il RentalPayment viene creato solo
    //quando il Rental non è più eliminabile. Dunque l'evenienza che il Rental e che questo incida
    // sul RentalPayment relativo non c'è.
    private Rental rental;

    public RentalPayment(){
        super();
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
