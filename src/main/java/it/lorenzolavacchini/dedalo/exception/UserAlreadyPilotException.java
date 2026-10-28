package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyPilotException extends RuntimeException{

    public UserAlreadyPilotException(){
        super("User is already a staff member");
    }

}