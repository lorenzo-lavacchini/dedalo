package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyAdminException extends RuntimeException{

    public UserAlreadyAdminException(){
        super("User is already an admin");
    }

}


