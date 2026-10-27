package it.lorenzolavacchini.dedalo.controller;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import it.lorenzolavacchini.dedalo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController{

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.getDTOById(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> usersDTO = userService.getAll();
        return ResponseEntity.ok(usersDTO);
    }

}



/*
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RestController
    @RequestMapping("/api/users")
    @PostMapping
    public ResponseEntity<UsereDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
*/