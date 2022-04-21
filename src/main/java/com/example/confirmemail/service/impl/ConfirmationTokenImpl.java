package com.example.confirmemail.service.impl;

import java.util.Optional;

import com.example.confirmemail.entity.ConfirmationToken;
import com.example.confirmemail.repository.ConfirmationTokenRepository;
import com.example.confirmemail.service.ConfirmationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenImpl implements ConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmTokenRepository.save(confirmationToken);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmTokenRepository.findByToken(token);
    }
    
}

