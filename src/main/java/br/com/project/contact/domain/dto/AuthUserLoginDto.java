package br.com.project.contact.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthUserLoginDto(

        @NotBlank(message = "Email is mandatory!")
        @Email
        String email,

        @NotBlank(message = "Password is mandatory!")
        @Size(max = 20, min = 8, message = "Min length = 8, Max length = 20")
        String password

) {

}
