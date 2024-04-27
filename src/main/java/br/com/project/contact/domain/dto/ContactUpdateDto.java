package br.com.project.contact.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ContactUpdateDto(

        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        LocalDate birthDate
) {
}
