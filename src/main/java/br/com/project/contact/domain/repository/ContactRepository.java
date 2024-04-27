package br.com.project.contact.domain.repository;

import br.com.project.contact.domain.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Page<Contact> findByName(String name, Pageable pageable);

    public List<Contact> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
}
