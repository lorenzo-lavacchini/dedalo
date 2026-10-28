package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    boolean existsByIdAndAdminRoleIsNotNull(Long id);

    boolean existsByAdminRoleIsNotNull();

    boolean existsByEmailAndIdNot(String email, Long id);

}
