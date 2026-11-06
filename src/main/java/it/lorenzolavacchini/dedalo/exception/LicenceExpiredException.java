package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LicenceExpiredException extends RuntimeException{

    public LicenceExpiredException(){
        super("Licence expired");
    }

}