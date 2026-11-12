package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.domain.Logbook;
import it.lorenzolavacchini.dedalo.domain.Pilot;
import it.lorenzolavacchini.dedalo.domain.User;
import it.lorenzolavacchini.dedalo.dto.logbook.LogbookDTO;
import it.lorenzolavacchini.dedalo.exception.LogbookIsNullException;
import it.lorenzolavacchini.dedalo.exception.PilotNotFoundException;
import it.lorenzolavacchini.dedalo.exception.UserIsNotPilotException;
import it.lorenzolavacchini.dedalo.exception.UserNotFoundException;
import it.lorenzolavacchini.dedalo.mapper.LogbookMapper;
import it.lorenzolavacchini.dedalo.repository.LogbookRepository;
import it.lorenzolavacchini.dedalo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LogbookService {

    private final LogbookRepository logbookRepository;
    private final UserRepository userRepository;
    private final LogbookMapper logbookMapper;

    public LogbookService(LogbookRepository logbookRepository, UserRepository userRepository, LogbookMapper logbookMapper) {
        this.logbookRepository = logbookRepository;
        this.userRepository = userRepository;
        this.logbookMapper = logbookMapper;
    }

    public LogbookDTO getByUserId(Long userId){

        User user = userRepository.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new UserNotFoundException());

        Pilot pilot = user.getPilotRole();

        if (pilot == null){
            throw new UserIsNotPilotException();
        }

        Logbook logbook = pilot.getLogbook();

        if (logbook == null){
            throw new LogbookIsNullException();
        }

        LogbookDTO logbookDTO = logbookMapper.toLogbookDTO(logbook);

        return logbookDTO;
    }

}
