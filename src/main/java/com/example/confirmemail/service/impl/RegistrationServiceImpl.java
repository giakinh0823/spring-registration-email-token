package com.example.confirmemail.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.example.confirmemail.entity.ConfirmationToken;
import com.example.confirmemail.entity.Role;
import com.example.confirmemail.entity.User;
import com.example.confirmemail.model.RegistrationRequest;
import com.example.confirmemail.service.ConfirmationTokenService;
import com.example.confirmemail.service.RegistrationService;
import com.example.confirmemail.service.UserService;
import com.example.confirmemail.validator.EmailValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValid = emailValidator.test(request.getEmail());
        if(!isValid){
            throw new IllegalStateException("Email not valid!");
        }
        User user = new User();
        user.setName(request.getFirstName() + " " + request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        return userService.signUp(user);
    }

    @Transactional
    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                                                .getToken(token)
                                                .orElseThrow(() -> 
                                                        new IllegalStateException("Token not found!"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed!");
        }

        LocalDateTime expectedAt = confirmationToken.getExpectedAt();
        if(expectedAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired!");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return token;
    }
}
