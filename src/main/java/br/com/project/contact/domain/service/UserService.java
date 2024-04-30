package br.com.project.contact.domain.service;


import br.com.project.contact.domain.dto.UserShowDto;
import br.com.project.contact.domain.dto.UserSignUpDto;
import br.com.project.contact.domain.dto.UserUpdateDto;
import br.com.project.contact.domain.model.User;
import br.com.project.contact.domain.repository.UserRepository;
import br.com.project.contact.infra.exception.ContactNotFoundException;
import br.com.project.contact.infra.exception.InvalidPasswordException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserShowDto save(UserSignUpDto userSignUpDto){
        String passwordEncrypted = new BCryptPasswordEncoder().encode(userSignUpDto.password());

        User user = new User();
        BeanUtils.copyProperties(userSignUpDto, user);
        user.setPassword(passwordEncrypted);

        return new UserShowDto(userRepository.save(user));
    }

    public UserShowDto findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return new UserShowDto(user.get());
        } else{
            throw new ContactNotFoundException("User not found.");
        }
    }

    public UserShowDto updateUser(UserUpdateDto userUpdateDto){
        Optional<User> user0 = userRepository.findById(userUpdateDto.userId());
        if (user0.isPresent()){
            User existingUser = user0.get();

            if(existingUser.getPassword().equals(userUpdateDto.password())){
                BeanUtils.copyProperties(userUpdateDto, existingUser);
                return new UserShowDto(userRepository.save(existingUser));
            }else {
                throw new InvalidPasswordException("Wrong password!");
            }
        } else {
            throw new ContactNotFoundException("User not found.");
        }
    }

    public void deleteId(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(user.get().getUserId());
        } else {
            throw new ContactNotFoundException("User not found!");
        }
    }
}
