package it.lorenzolavacchini.dedalo.controller;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestCreateDTO;
import it.lorenzolavacchini.dedalo.dto.abilitationprofile.AbilitationProfileRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestUpdateDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import it.lorenzolavacchini.dedalo.mapper.UserMapper;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import it.lorenzolavacchini.dedalo.service.PilotService;
import it.lorenzolavacchini.dedalo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{

    private final UserService userService;
    private final PilotService pilotService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserService userService, PilotService pilotService, UserRepository userRepository, UserMapper userMapper){
        this.userService = userService;
        this.pilotService = pilotService;
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
        UserResponseDTO userResponseDTO = userService.create(userRequestCreateDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserRequestUpdateDTO userRequestUpdateDTO){
        UserResponseDTO userResponseDTO = userService.update(id, userRequestUpdateDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/staff-role/{id}")
    public ResponseEntity<UserResponseDTO> assignStaffRole(@PathVariable Long id){
        UserResponseDTO userResponseDTO = userService.assignStaffRole(id);
        return ResponseEntity.ok(userResponseDTO);
    }

    @PatchMapping("/pilot/{id}")
    public ResponseEntity<UserResponseDTO> promoteToPilot(@PathVariable Long id, @Valid @RequestBody AbilitationProfileRequestDTO abilitationProfileRequestDTO){
        UserResponseDTO userResponseDTO = userService.promoteToPilot(id, abilitationProfileRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    //Poi aggiornare anche i metodi qui sopra che fanno la get per far recuperare solo gli user attivi!
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        userService.deleteLogic(id);
        return ResponseEntity.ok("Resource correctly deleted");
    }

    @PatchMapping("/users/{userId}/pilot/association")
    public ResponseEntity<PilotResponseDTO> makeAssociated(@PathVariable Long id){
        PilotResponseDTO pilotResponseDTO = pilotService.makeAssociated(id);
        return ResponseEntity.ok(pilotResponseDTO);
    }
}