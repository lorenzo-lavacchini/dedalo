package it.lorenzolavacchini.dedalo.dto.airplane;

public class RentableAirplaneResponseDTO extends AirplaneResponseDTO {
    private int hourlyRate;

    public int getHourlyRate() { return hourlyRate; }

    public void setHourlyRate(int hourlyRate) { this.hourlyRate = hourlyRate; }
}
