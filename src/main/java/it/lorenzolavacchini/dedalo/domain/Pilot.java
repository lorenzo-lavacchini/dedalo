package it.lorenzolavacchini.dedalo.domain;

import java.util.List;

public class Pilot {

    private boolean isAssociated;

    private User user;

    private List<Payment> payments; //inizializzare in maniera Lazy! Oppure rimuoverlo proprio

    private List<Rental> rentals; //inizializzare in maniera Lazy! Oppure rimuoverlo proprio

    private Logbook logbook;

    private AbilitationProfile abilitationProfile;

    private PrivateAirplane ownedAirplanes;
}
