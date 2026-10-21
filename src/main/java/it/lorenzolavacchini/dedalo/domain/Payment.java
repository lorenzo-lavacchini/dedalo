package it.lorenzolavacchini.dedalo.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "payment_type",
        discriminatorType = DiscriminatorType.STRING
)
//NOTA: cancellazione di istanze di classe astratta o classi derivate non portano problemi e non necessitano di annotazioni particolari.
//Questo perché tutte le istanze stanno in un'unica tabella, e quindi non si vengono a creare problemi con le associazioni e le foreign key.
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pilot pilot;

    public Payment(){

    }

}
