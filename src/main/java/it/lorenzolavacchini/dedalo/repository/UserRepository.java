package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    boolean existsByIdAndAdminRoleIsNotNull(Long id);

    boolean existsByAdminRoleIsNotNull();

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<User> findByIdAndDeletedFalse(Long id);

    List<User> findByDeletedFalse();
}
