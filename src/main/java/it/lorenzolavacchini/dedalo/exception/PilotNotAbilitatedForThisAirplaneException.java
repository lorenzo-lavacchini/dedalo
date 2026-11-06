package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PilotNotAbilitatedForThisAirplaneException extends RuntimeException{

    public PilotNotAbilitatedForThisAirplaneException(){
        super("Pilot is not abilitated to flight using this type of Airplane");
    }

}