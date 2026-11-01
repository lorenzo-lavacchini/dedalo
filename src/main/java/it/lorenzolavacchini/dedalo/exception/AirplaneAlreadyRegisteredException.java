package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AirplaneAlreadyRegisteredException extends RuntimeException{

    public AirplaneAlreadyRegisteredException(){
        super("Airplane with the same Registration Number already registered");
    }

}