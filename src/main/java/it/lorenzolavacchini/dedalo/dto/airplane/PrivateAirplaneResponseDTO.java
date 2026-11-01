package it.lorenzolavacchini.dedalo.dto.airplane;

public class PrivateAirplaneResponseDTO extends AirplaneResponseDTO {

    private Long ownerId;

    public Long getOwnerId() { return ownerId; }

    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

}
