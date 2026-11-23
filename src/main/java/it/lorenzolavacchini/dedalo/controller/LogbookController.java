package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import it.lorenzolavacchini.dedalo.service.LogbookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogbookController {

    private final LogbookService logbookService;

    public LogbookController(LogbookService logbookService) {
        this.logbookService = logbookService;
    }

    @GetMapping("users/{userId}/pilot/logbook/")
    public ResponseEntity<LogbookDTO> getLogbook(@PathVariable Long userId){

        LogbookDTO logbookDTO = logbookService.getByUserId(userId);

        return ResponseEntity.ok(logbookDTO);
    }

}

//prova