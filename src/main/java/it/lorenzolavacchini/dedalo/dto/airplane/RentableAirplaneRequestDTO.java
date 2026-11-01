package it.lorenzolavacchini.dedalo.dto.airplane;

import jakarta.validation.constraints.Min;

public class RentableAirplaneRequestDTO extends AirplaneRequestDTO {

    @Min(value = 1, message = "La tariffa oraria deve essere maggiore di zero")
    private int hourlyRate;

    public int getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(int hourlyRate) { this.hourlyRate = hourlyRate; }
}
