package it.lorenzolavacchini.dedalo.dto.airplane;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrivateAirplaneRequestDTO.class, name = "PRIVATE"),
        @JsonSubTypes.Type(value = RentableAirplaneRequestDTO.class, name = "RENTABLE")
})
public abstract class AirplaneRequestDTO {

    @NotBlank(message = "Il numero di registrazione è obbligatorio")
    @Size(min = 1, max = 6, message = "Il numero di registrazione deve essere al massimo di 6 caratteri")
    private String registrationNumber;

    @NotNull(message = "Specificare se l'aeroplano è avanzato o meno")
    private boolean advanced;

    @NotBlank(message = "Il nome del modello è obbligatorio")
    @Size(max = 50, message = "Il nome del modello non può superare i 50 caratteri")
    private String modelName;

    public AirplaneRequestDTO() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
