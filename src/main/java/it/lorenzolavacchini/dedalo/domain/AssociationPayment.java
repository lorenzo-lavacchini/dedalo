package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AssociationPayment")
public class AssociationPayment extends Payment {

    @Column(nullable = false)
    private java.time.Year associationYear;

    public AssociationPayment(){

    }

}
