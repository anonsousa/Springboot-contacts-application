package br.com.project.contact.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContactUpdateDto(

        @NotNull(message = "Id is mandatory!")
        Long id,

        @NotBlank(message = "Name is mandatory!")
                @Size(min = 3, max = 100, message = "Min length for name:2, max: 99")
        String name,

        @NotBlank(message = "Email is mandatory!")
        @Email(message = "Please fill email with a valid format!")
        String email,

        @NotBlank(message = "Password is mandatory!")
        String password,

        @NotNull(message = "Birthdate is mandatory!")
        LocalDate birthDate
) {
}
