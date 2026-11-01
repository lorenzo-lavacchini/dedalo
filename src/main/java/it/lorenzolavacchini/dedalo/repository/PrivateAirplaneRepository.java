package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import it.lorenzolavacchini.dedalo.domain.PrivateAirplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivateAirplaneRepository extends JpaRepository<PrivateAirplane,Long> {

    List<PrivateAirplane> findByDeletedFalseAndOwner_Id(Long id);
}
