package br.com.project.contact.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(

        @NotNull(message = "Id is mandatory!")
        Long userId,

        @NotBlank(message = "Name is mandatory!")
        String name,

        @NotBlank(message = "Email is mandatory!")
        @Email
        String email,

        @NotBlank(message = "Password is mandatory!")
        String password


) {
}
