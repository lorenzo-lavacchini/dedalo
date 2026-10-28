package it.lorenzolavacchini.dedalo.controller;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestCreateDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestUpdateDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import it.lorenzolavacchini.dedalo.mapper.UserMapper;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import it.lorenzolavacchini.dedalo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{

    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserRepository userRepository, UserMapper userMapper){
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.getDTOById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> usersDTO = userService.getAll();
        return ResponseEntity.ok(usersDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestCreateDTO userRequestCreateDTO){
        User user = userService.create(userRequestCreateDTO);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestUpdateDTO userRequestUpdateDTO){
        User user = userService.update(id, userRequestUpdateDTO);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/staff-role/{id}")
    public ResponseEntity<UserResponseDTO> assignStaffRole(@PathVariable Long id){
        userService.assignStaffRole(id);
        User user = userService.getById(id);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/pilot/{id}")
    public ResponseEntity<UserResponseDTO> promoteToPilot(@PathVariable Long id, @Valid @RequestBody AbilitationProfileRequestDTO abilitationProfileRequestDTO){
        userService.promoteToPilot(id, abilitationProfileRequestDTO);
        User user = userService.getById(id);
        UserResponseDTO userResponseDTO = userMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(userResponseDTO);
    }



}