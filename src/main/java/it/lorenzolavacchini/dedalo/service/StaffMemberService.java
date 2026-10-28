package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.StaffMember;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.repository.StaffMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffMemberService {

    private final StaffMemberRepository staffMemberRepository;

    public StaffMemberService(StaffMemberRepository staffMemberRepository){
        this.staffMemberRepository = staffMemberRepository;
    }

    public StaffMember create(User user){

        StaffMember staffMemberRole = new StaffMember(user);
        staffMemberRepository.save(staffMemberRole);
        return staffMemberRole;
    }
}
