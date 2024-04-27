package br.com.project.contact.domain.dto;

import br.com.project.contact.domain.model.Contact;

import java.time.LocalDate;


public record ContactShowDto(

        Long id,

        String name,

        String email,

        LocalDate birthDate


) {
    public ContactShowDto(Contact contact){
        this(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getBirthDate()
        );
    }
}
