package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.repository.AdminRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;


    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin createAdmin(User user){
        Admin admin = new Admin(user);
        adminRepository.save(admin);
        return admin;
    }
}
