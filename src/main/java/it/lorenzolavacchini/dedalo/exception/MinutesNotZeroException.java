package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MinutesNotZeroException extends RuntimeException{

    public MinutesNotZeroException(){
        super("Minutes are not set in 00 form");
    }

}