package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.Rental;
import it.lorenzolavacchini.dedalo.domain.RentalState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByRentedAirplane_IdAndDate(Long airplaneId, LocalDate date);

}
