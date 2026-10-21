package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.RentableAirplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentableAirplaneRepository extends JpaRepository<RentableAirplane, Long> {
}
