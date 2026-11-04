package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentableAirplaneNotFoundException extends RuntimeException{

    public RentableAirplaneNotFoundException(){
        super("Rentable airplane with specified id not found ");
    }

}
