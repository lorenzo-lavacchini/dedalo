package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PastDateException extends RuntimeException{

    public PastDateException(){
        super("Date can't be in the past.");
    }

}