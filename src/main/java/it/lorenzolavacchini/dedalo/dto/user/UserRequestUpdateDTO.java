package it.lorenzolavacchini.dedalo.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequestUpdateDTO {

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Size(max = 50)
    private String surname;

    @NotBlank(message = "Il telefono è obbligatorio")
    @Size(max = 50)
    private String telephone;

    @NotBlank(message = "L'email è obbligatoria")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(max = 50)
    private String password;

    public UserRequestUpdateDTO() {}

    public UserRequestUpdateDTO(String name, String surname, String telephone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

