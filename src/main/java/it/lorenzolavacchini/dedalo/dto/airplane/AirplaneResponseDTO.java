package it.lorenzolavacchini.dedalo.dto.airplane;

public abstract class AirplaneResponseDTO {

    private Long id;
    private String registrationNumber;
    private boolean advanced;
    private String modelName;
    private String type;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRegistrationNumber() { return registrationNumber; }

    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public boolean isAdvanced() { return advanced; }

    public void setAdvanced(boolean advanced) { this.advanced = advanced; }

    public String getModelName() { return modelName; }

    public void setModelName(String modelName) { this.modelName = modelName; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}

