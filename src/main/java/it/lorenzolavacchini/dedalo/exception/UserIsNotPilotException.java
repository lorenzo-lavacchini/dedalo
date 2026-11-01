package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserIsNotPilotException extends RuntimeException{

    public UserIsNotPilotException(){
        super("User is not a Pilot");
    }

}