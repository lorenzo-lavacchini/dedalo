package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyAdminException extends RuntimeException{

    public UserAlreadyAdminException(String message){
        super(message);
    }

}


