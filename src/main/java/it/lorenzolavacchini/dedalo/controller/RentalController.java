package it.lorenzolavacchini.dedalo.controller;

import it.lorenzolavacchini.dedalo.dto.rental.RentalRequestDTO;
import it.lorenzolavacchini.dedalo.dto.rental.RentalResponseDTO;
import it.lorenzolavacchini.dedalo.dto.timeslots.TimeSlotDTO;
import it.lorenzolavacchini.dedalo.service.RentalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<TimeSlotDTO>> getAvailableSlots(@RequestParam Long airplaneId,
                                                               @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date){
        List<TimeSlotDTO> timeSlots = rentalService.getAvailableSlots(airplaneId, date);
        return ResponseEntity.ok(timeSlots);
    }

    @PostMapping
    public ResponseEntity<RentalResponseDTO> create(@RequestBody RentalRequestDTO rentalRequestDTO){

        RentalResponseDTO rentalResponseDTO = rentalService.create(rentalRequestDTO);
        return ResponseEntity.ok(rentalResponseDTO);
    }





}
