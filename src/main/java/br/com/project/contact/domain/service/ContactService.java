package br.com.project.contact.domain.service;

import br.com.project.contact.domain.dto.ContactSaveDto;
import br.com.project.contact.domain.dto.ContactShowDto;
import br.com.project.contact.domain.dto.ContactUpdateDto;
import br.com.project.contact.domain.model.Contact;
import br.com.project.contact.domain.repository.ContactRepository;
import br.com.project.contact.infra.exception.ContactNotFoundException;
import br.com.project.contact.infra.exception.InvalidPasswordException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactShowDto save(ContactSaveDto contactSaveDto){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactSaveDto, contact);

        return new ContactShowDto(contactRepository.save(contact));
    }



    public ContactShowDto findOneId(Long id){
        Optional<Contact> contact0 = contactRepository.findById(id);
        if (contact0.isPresent()){
            return new ContactShowDto(contact0.get());
        }
        else {
            throw new ContactNotFoundException("Contact not found");
        }
    }



    public Page<ContactShowDto> findAllIds(Pageable pageable){
        Page<Contact> contactPage = contactRepository.findAll(pageable);
        return contactPage.map(contact -> new ContactShowDto(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate()));
    }


    public Page<ContactShowDto> findBirthdays(LocalDate startDate, LocalDate endDate, Pageable pageable){
        Page<Contact> contactPage = contactRepository.findByBirthDateBetween(startDate, endDate, pageable);
        if (!contactPage.isEmpty()){
            return contactPage.map(contact -> new ContactShowDto(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate()));
        }else{
            throw new ContactNotFoundException("We don't have any birthDate based on this Query.");
        }
    }


    public Page<ContactShowDto> findAllbyName(String name, Pageable pageable){
        Page<Contact> contactPage = contactRepository.findByName(name, pageable);
        if(!contactPage.isEmpty()){
            return contactPage.map(contact -> new ContactShowDto(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate()));
        }else{
            throw new ContactNotFoundException("We don't have this name on our database.");
        }
    }

    public Page<ContactShowDto> findAllByNameWithQuery(String name, Pageable pageable){
        Page<Contact> contactPage = contactRepository.findContactByName(name, pageable);
        if(!contactPage.isEmpty()){
            return contactPage.map(contact -> new ContactShowDto(contact.getId(), contact.getName(), contact.getEmail(), contact.getBirthDate()));
        }else {
            throw new ContactNotFoundException("We don't have this name on our database.");
        }
    }


    public ContactShowDto update(ContactUpdateDto contactUpdateDto){
        Optional<Contact> contact0 = contactRepository.findById(contactUpdateDto.id());
        if (contact0.isPresent()){
            Contact existingContact = contact0.get();

            if (existingContact.getPassword().equals(contactUpdateDto.password())){
                var contact = new Contact();
                BeanUtils.copyProperties(contactUpdateDto, existingContact);
                return new ContactShowDto(contactRepository.save(existingContact));
            }else {
                throw new InvalidPasswordException("Wrong password!");
            }
        }
        else {
            throw new ContactNotFoundException("Contact not found.");
        }
    }


    public void deleteId(Long id){
        Optional<Contact> contact0 = contactRepository.findById(id);
        if (contact0.isPresent()){
            contactRepository.deleteById(contact0.get().getId());
        }
        else {
            throw new ContactNotFoundException("Contact not found.");
        }
    }
}
