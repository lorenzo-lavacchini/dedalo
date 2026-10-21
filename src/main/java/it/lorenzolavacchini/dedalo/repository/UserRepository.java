package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {



}
