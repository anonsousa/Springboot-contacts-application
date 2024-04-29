package br.com.project.contact.controller;

import br.com.project.contact.domain.dto.UserSignUpDto;
import br.com.project.contact.domain.dto.UserUpdateDto;
import br.com.project.contact.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity save(@RequestBody @Valid UserSignUpDto userSignUpDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userSignUpDto));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity findById(@PathVariable(value = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/users")
    public ResponseEntity updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userUpdateDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id")Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully!");
    }
}
