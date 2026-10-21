package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.StaffMember;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PilotService pilotService;

    public UserService(UserRepository userRepository, PilotService pilotService){
        this.userRepository = userRepository;
        this.pilotService = pilotService;
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
}
