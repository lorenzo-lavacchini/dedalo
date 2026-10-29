package it.lorenzolavacchini.dedalo.dto.pilot;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"associated"})
public class PilotResponseDTO {

    private boolean associated;

    public boolean isAssociated() {
        return associated;
    }

    public void setAssociated(boolean associated) {
        this.associated = associated;
    }
}
