package br.com.project.contact.controller;

import br.com.project.contact.domain.dto.ContactSaveDto;
import br.com.project.contact.domain.dto.ContactShowDto;
import br.com.project.contact.domain.dto.ContactUpdateDto;
import br.com.project.contact.domain.model.Contact;
import br.com.project.contact.domain.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ContactSaveDto contactSaveDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactSaveDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneId(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findOneId(id));
    }

    @GetMapping
    public Page<ContactShowDto> getAllIds(Pageable pageable){
        return contactService.findAllIds(pageable);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ContactShowDto>> getName(@PathVariable(value = "name")String name,
                                                        Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAllbyName(name, pageable));
    }

    @GetMapping("/birthday/{startDate}/{endDate}")
    public ResponseEntity<Page<ContactShowDto>> getByBirthday(@PathVariable(value = "startDate") LocalDate startDate,
                                                              @PathVariable(value = "endDate")LocalDate endDate,
                                                              Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findBirthdays(startDate, endDate, pageable));
    }

    @GetMapping("/names/{name}")
    public ResponseEntity<Page<ContactShowDto>> getByNameWithQuery(@PathVariable(value = "name")String name,
                                                                   Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.findAllByNameWithQuery(name, pageable));
    }

    @PutMapping
    public ResponseEntity updateId(@RequestBody @Valid ContactUpdateDto contactUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(contactService.update(contactUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteId(@PathVariable(value = "id") Long id){
        contactService.deleteId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Contact deleted successfully!");
    }
}
