package br.com.project.contact.controller;

import br.com.project.contact.domain.dto.AuthUserLoginDto;
import br.com.project.contact.domain.dto.UserShowDto;
import br.com.project.contact.domain.dto.UserSignUpDto;
import br.com.project.contact.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthUserLoginDto authUserLoginDto){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        authUserLoginDto.email(),
                        authUserLoginDto.password()
                );

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserSignUpDto userSignUpDto){
        UserShowDto userSaved = null;
        userSaved = userService.save(userSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }
}
