package it.lorenzolavacchini.dedalo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedRadioAbilitationException extends RuntimeException{

    public NotAllowedRadioAbilitationException(){
        super("Not allowed radio abilitation. Two seats abilitation missing");
    }

}