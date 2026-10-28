package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Admin;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.StaffMember;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestCreateDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestUpdateDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import it.lorenzolavacchini.dedalo.exception.*;
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
    private final StaffMemberService staffMemberService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, PilotService pilotService, AdminService adminService,
                       StaffMemberService staffMemberService,  UserMapper userMapper){
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.pilotService = pilotService;
        this.adminService = adminService;
        this.staffMemberService = staffMemberService;
        this.userMapper = userMapper;
    }

    @Transactional
    public User create(UserRequestCreateDTO userRequestCreateDTO){

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmail(userRequestCreateDTO.getEmail())){
            throw new EmailAlreadyUsedException();
        }

        User user = userMapper.toEntity(userRequestCreateDTO);
        userRepository.save(user);
        return user;
    }


    @Transactional
    public User create(String name, String surname, String telephone, String email, String password){

        if(name.isBlank() || surname.isBlank() || telephone.isBlank() || email.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("Mancano dati obbiligatori");
        }

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyUsedException();
        }

        User user = new User(name, surname, telephone, email, password);
        userRepository.save(user);
        return user;
    }


    @Transactional
    public User update(Long id, UserRequestUpdateDTO userRequestUpdateDTO){

        User user = userMapper.toEntity(id, userRequestUpdateDTO);

        //Controlla che non esista già un utente creato con quell'email
        if(userRepository.existsByEmailAndIdNot(user.getEmail(), id)){
            throw new EmailAlreadyUsedException();
        }

        userRepository.save(user);
        return user;

    }

    public User getById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return userRepository.getReferenceById(id);
    }

    public UserResponseDTO getDTOById(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

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
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }


    @Transactional
    public void promoteToAdmin(Long id){

        if(!userRepository.existsById(id)){
            throw new UserNotFoundException();
        }

        if(userRepository.existsByIdAndAdminRoleIsNotNull(id)){
            throw new UserAlreadyAdminException();
        }

        User user = userRepository.getReferenceById(id);
        Admin admin = adminService.createAdmin(user);
        user.setAdminRole(admin);
        userRepository.save(user);
    }

    @Transactional
    public void assignStaffRole(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        if(user.getStaffMemberRole() != null){
            throw new UserAlreadyStaffException();
        }

        StaffMember staffMemberRole = staffMemberService.create(user);
        user.setStaffMemberRole(staffMemberRole);
        userRepository.save(user);
    }

    @Transactional
    public void promoteToPilot(Long id, AbilitationProfileRequestDTO abilitationProfileRequestDTO){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserAlreadyPilotException());

        if(user.getPilotRole() != null){
            throw new UserAlreadyPilotException();
        }

        Pilot pilotRole = pilotService.create(user, abilitationProfileRequestDTO);
        user.setPilotRole(pilotRole);
        userRepository.save(user);
    }
}
