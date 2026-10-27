package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import it.lorenzolavacchini.dedalo.exception.UserAlreadyAdminException;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.UserMapper;
import it.lorenzolavacchini.dedalo.repository.AdminRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PilotService pilotService;
    private final AdminService adminService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, PilotService pilotService, AdminService adminService, UserMapper userMapper){
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.pilotService = pilotService;
        this.adminService = adminService;
        this.userMapper = userMapper;
    }

    @Transactional
    public User createUser(UserRequestDTO userRequestDTO){

        if(userRequestDTO.getName().isBlank() || userRequestDTO.getSurname().isBlank() || userRequestDTO.getTelephone().isBlank()
                || userRequestDTO.getEmail().isBlank() || userRequestDTO.getPassword().isBlank()){
            throw new IllegalArgumentException("Lack of necessary data");
        }

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new IllegalArgumentException("Email already used");
        }

        User user = new User(userRequestDTO.getName(), userRequestDTO.getSurname(), userRequestDTO.getTelephone(), userRequestDTO.getEmail(), userRequestDTO.getPassword());
        userRepository.save(user);
        return user;
    }


    @Transactional
    public User createUser(String name, String surname, String telephone, String email, String password){

        if(name.isBlank() || surname.isBlank() || telephone.isBlank() || email.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("Mancano dati obbiligatori");
        }

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Esiste già un utente registrato con questa email");
        }

        User user = new User(name, surname, telephone, email, password);
        userRepository.save(user);
        return user;
    }

    public User getById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return userRepository.getReferenceById(id);
    }

    public UserResponseDTO getDTOById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);

        return userResponseDTO;
    }

    public List<UserResponseDTO> getAll(){

        List<User> users = userRepository.findAll();

        List<UserResponseDTO> usersDTO = new ArrayList<>();

        if(users.isEmpty()){
            return usersDTO ;
        }

        for (User user : users){
            usersDTO.add(userMapper.toUserResponseDTO(user));
        }

        return usersDTO;

    }

    @Transactional
    public void delete(Long id){

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
        userRepository.save(user);
    }
}
