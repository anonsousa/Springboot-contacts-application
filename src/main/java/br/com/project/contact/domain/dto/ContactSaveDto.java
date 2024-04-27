package br.com.project.contact.domain.dto;

import br.com.project.contact.domain.model.Contact;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContactSaveDto(

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+$", message = "Invalid name.")
    String name,

    @NotNull
    @Email
    String email,

    @NotBlank
            @Size(min = 8, max = 16, message = "Min length 8, max 16.")
    String password,

    @NotNull
    LocalDate birthDate

) {
}
