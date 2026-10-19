package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class RentalPayment extends Payment{

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
}
