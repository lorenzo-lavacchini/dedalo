package it.lorenzolavacchini.dedalo.dto.logbook;

import it.lorenzolavacchini.dedalo.domain.Flight;

import java.time.LocalDate;
import java.util.List;

public class LogbookDTO {

    private LocalDate creationDate;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
