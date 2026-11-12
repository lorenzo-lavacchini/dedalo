package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Logbook;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import it.lorenzolavacchini.dedalo.dto.pilot.PilotResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LogbookMapper {

    public LogbookDTO toLogbookDTO(Logbook logbook){

        if(logbook == null){
            throw new IllegalArgumentException("Can't map a null object to DTO");
        }

        LogbookDTO logbookDTO = new LogbookDTO();

        logbookDTO.setCreationDate(logbook.getCreationDate());

        return logbookDTO;
    }


}
