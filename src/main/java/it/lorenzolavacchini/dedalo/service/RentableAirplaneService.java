package it.lorenzolavacchini.dedalo.service;

import it.lorenzolavacchini.dedalo.repository.RentableAirplaneRepository;
import org.springframework.stereotype.Service;

@Service
public class RentableAirplaneService {

    private final RentableAirplaneRepository rentableAirplaneRepository;

    public RentableAirplaneService(RentableAirplaneRepository rentableAirplaneRepository) {
        this.rentableAirplaneRepository = rentableAirplaneRepository;
    }

    public boolean exists(Long id){
        return rentableAirplaneRepository.existsById(id);
    }

}
