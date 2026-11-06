package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PilotNotAssociatedException extends RuntimeException{

    public PilotNotAssociatedException(){
        super("Licence expired");
    }

}