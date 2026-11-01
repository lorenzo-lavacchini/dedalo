package it.lorenzolavacchini.dedalo.dto.airplane;


import jakarta.validation.constraints.NotNull;

public class PrivateAirplaneRequestDTO extends AirplaneRequestDTO {

    @NotNull(message = "L'ID del pilota proprietario è obbligatorio")
    private Long ownerId;

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}

