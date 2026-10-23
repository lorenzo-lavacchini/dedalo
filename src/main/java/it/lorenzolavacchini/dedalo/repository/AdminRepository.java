package it.lorenzolavacchini.dedalo.repository;

import it.lorenzolavacchini.dedalo.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
