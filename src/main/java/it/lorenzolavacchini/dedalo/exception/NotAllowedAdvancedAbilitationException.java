package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedAdvancedAbilitationException extends RuntimeException{

    public NotAllowedAdvancedAbilitationException(){
        super("Not allowed advanced abilitation. Radio abilitation missing");
    }

}