package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.PrivateAirplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivateAirplaneRepository extends JpaRepository<PrivateAirplane,Long> {
}
