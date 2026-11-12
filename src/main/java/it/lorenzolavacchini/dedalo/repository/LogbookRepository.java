package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.Logbook;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogbookRepository extends JpaRepository<Logbook,Long> {

    Optional<Logbook> findByOwner_UserId(long userId);

}
