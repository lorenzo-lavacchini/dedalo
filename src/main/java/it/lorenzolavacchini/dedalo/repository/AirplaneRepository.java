package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane,Long> {

    boolean existsByRegistrationNumber(String registrationNumber);

    List<Airplane> findByDeletedFalse();

    Optional<Airplane> findByIdAndDeletedFalse(Long id);

}
