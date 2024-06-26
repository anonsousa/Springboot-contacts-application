package br.com.project.contact.domain.dto;

import br.com.project.contact.domain.model.User;
import br.com.project.contact.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserShowDto(
        Long userId,

        @NotBlank(message = "Name is mandatory!")
        String name,

        @NotBlank(message = "Email is mandatory!")
        @Email
        String email,

        UserRole role
) {
    public UserShowDto(User user){
        this(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
