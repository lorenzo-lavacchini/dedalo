package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AirplaneTypeNotValidException extends RuntimeException{

    public AirplaneTypeNotValidException(){
        super("Airplane type not valid");
    }

}