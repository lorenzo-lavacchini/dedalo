package it.lorenzolavacchini.dedalo.domain;

import java.time.LocalTime;
import java.util.Date;

public abstract class Payment {

    private Long id;

    private int amount;

    private PaymentState paymentState;
}
