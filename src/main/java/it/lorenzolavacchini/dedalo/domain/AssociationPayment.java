package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.Year;

@Entity
@DiscriminatorValue("AssociationPayment")
public class AssociationPayment extends Payment {

    @Column(nullable = false)
    private java.time.Year associationYear;

    public AssociationPayment(){

    }

    public Year getAssociationYear() {
        return associationYear;
    }

    public void setAssociationYear(Year associationYear) {
        this.associationYear = associationYear;
    }
}
