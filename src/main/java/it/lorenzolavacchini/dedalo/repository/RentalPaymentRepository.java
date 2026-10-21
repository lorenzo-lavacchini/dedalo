package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.RentalPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalPaymentRepository extends JpaRepository<RentalPayment, Long> {
}
