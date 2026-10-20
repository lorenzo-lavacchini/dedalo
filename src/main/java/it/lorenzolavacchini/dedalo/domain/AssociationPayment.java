package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("AssociationPayment")
public class AssociationPayment extends Payment {

    private java.time.Year yearOfAssociation;

}
