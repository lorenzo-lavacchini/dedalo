package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.StaffMember;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.exception.UserAlreadyAdminException;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.repository.AdminRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PilotService pilotService;
    private final AdminService adminService;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, PilotService pilotService, AdminService adminService){
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.pilotService = pilotService;
        this.adminService = adminService;
    }

    public User getReferenceById(Long id){
        return userRepository.getReferenceById(id);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(String name, String surname, String telephone, String email, String password){

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("An user having ths email already exists: use another email to register.");
        }

        User user = new User(name, surname, telephone, email, password);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void deleteUser(Long id){

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found");
        }

        userRepository.deleteById(id);

    }

    @Transactional
    public void promoteToAdmin(Long id){

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found");
        }

        if(userRepository.existsByIdAndAdminRoleIsNotNull(id)){
            throw new UserAlreadyAdminException("User is already an admin");
        }

        User user = userRepository.getReferenceById(id);
        Admin admin = adminService.createAdmin(user);
        user.setAdminRole(admin);
        //userRepository.save(user);
    }
}
