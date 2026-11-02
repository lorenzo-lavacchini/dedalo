package it.lorenzolavacchini.dedalo.mapper;

import it.lorenzolavacchini.dedalo.domain.Rental;
import it.lorenzolavacchini.dedalo.dto.rental.RentalResponseDTO;
import it.lorenzolavacchini.dedalo.repository.RentalRepository;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    private final RentalRepository rentalRepository;

    public RentalMapper(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public RentalResponseDTO toRentalResponseDTO(Rental rental){

        RentalResponseDTO rentalResponseDTO = new RentalResponseDTO();
        rentalResponseDTO.setStartTime(rental.getStartTime());
        rentalResponseDTO.setEndTime(rental.getEndTime());
        rentalResponseDTO.setAirplaneId(rental.getRentedAirplane().getId());
        rentalResponseDTO.setPilotId(rental.getRenterPilot().getId());

        return rentalResponseDTO;

    }


}
