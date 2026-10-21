package it.lorenzolavacchini.dedalo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.lorenzolavacchini.dedalo.domain.StaffMember;

public interface StaffMemberRepository extends JpaRepository<StaffMember, Long> {
}
