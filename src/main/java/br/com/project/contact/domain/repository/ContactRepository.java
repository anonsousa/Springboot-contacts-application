package br.com.project.contact.domain.repository;

import br.com.project.contact.domain.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByName(String name, Pageable pageable);

    @Query("SELECT c FROM Contact c WHERE c.name = :name")
    Page<Contact> findContactByName(@Param("name") String name, Pageable pageable);

    Page<Contact> findByBirthDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
