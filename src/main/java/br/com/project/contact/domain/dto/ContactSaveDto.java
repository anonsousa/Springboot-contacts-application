package br.com.project.contact.domain.dto;

import br.com.project.contact.domain.model.Contact;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContactSaveDto(

    @NotBlank(message = "Name is mandatory!")
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$", message = "Invalid name.")
    @Size(min = 3, max = 100, message = "Min length for name:2, max: 99")
    String name,

    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Please fill email with a valid format!")
    String email,

    @NotBlank(message = "Password is mandatory!")
            @Size(min = 8, max = 16, message = "Min length 8, max 16.")
    String password,

    @NotNull(message = "Birthdate can't be null!")
    LocalDate birthDate

) {
}
