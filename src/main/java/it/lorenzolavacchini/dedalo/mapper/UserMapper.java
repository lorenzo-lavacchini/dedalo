package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRequestDTO toUserRequestDTO(User user){

        if(user == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(user.getId());
        userRequestDTO.setName(user.getName());
        userRequestDTO.setSurname(user.getSurname());
        userRequestDTO.setTelephone(user.getTelephone());
        userRequestDTO.setEmail(user.getEmail());
        userRequestDTO.setPassword(user.getPassword());

        return userRequestDTO;
    }

    public UserResponseDTO toUserResponseDTO(User user){

        if(user == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setSurname(user.getSurname());
        userResponseDTO.setTelephone(user.getTelephone());
        userResponseDTO.setEmail(user.getEmail());

        if (user.getAdminRole() == null){
            userResponseDTO.setStaffMember(false);
        }
        else{
            userResponseDTO.setStaffMember(true);
        }

        if(user.getStaffMemberRole() == null){
            userResponseDTO.setPilot(false);
        }
        else{
            userResponseDTO.setPilot(true);
        }

        if (user.getPilotRole() == null){
            userResponseDTO.setPilot(false);
        }
        else{
            userResponseDTO.setPilot(true);
        }

        return userResponseDTO;
    }

    public User toEntity(UserRequestDTO userRequestDTO){
       if(userRequestDTO == null){
           throw new IllegalArgumentException("Can't map a null object to an entity");
       }

       User user = new User();
       user.setId(userRequestDTO.getId());
       user.setName(userRequestDTO.getName());
       user.setSurname(userRequestDTO.getSurname());
       user.setTelephone(userRequestDTO.getTelephone());
       user.setEmail(userRequestDTO.getEmail());
       user.setPassword(userRequestDTO.getPassword());

       return user;
    }

}
