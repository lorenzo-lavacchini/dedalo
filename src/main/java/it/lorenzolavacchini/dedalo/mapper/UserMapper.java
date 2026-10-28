package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.user.UserRequestUpdateDTO;
import it.lorenzolavacchini.dedalo.dto.user.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRequestUpdateDTO toUserRequestDTO(User user){

        if(user == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        UserRequestUpdateDTO userRequestUpdateDTO = new UserRequestUpdateDTO();
        userRequestUpdateDTO.setId(user.getId());
        userRequestUpdateDTO.setName(user.getName());
        userRequestUpdateDTO.setSurname(user.getSurname());
        userRequestUpdateDTO.setTelephone(user.getTelephone());
        userRequestUpdateDTO.setEmail(user.getEmail());
        userRequestUpdateDTO.setPassword(user.getPassword());

        return userRequestUpdateDTO;
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

    public User toEntity(UserRequestUpdateDTO userRequestUpdateDTO){
       if(userRequestUpdateDTO == null){
           throw new IllegalArgumentException("Can't map a null object to an entity");
       }

       User user = new User();
       user.setId(userRequestUpdateDTO.getId());
       user.setName(userRequestUpdateDTO.getName());
       user.setSurname(userRequestUpdateDTO.getSurname());
       user.setTelephone(userRequestUpdateDTO.getTelephone());
       user.setEmail(userRequestUpdateDTO.getEmail());
       user.setPassword(userRequestUpdateDTO.getPassword());

       return user;
    }

}
