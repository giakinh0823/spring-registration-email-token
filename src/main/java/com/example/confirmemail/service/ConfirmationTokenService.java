package com.example.confirmemail.service;

import java.util.Optional;

import com.example.confirmemail.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    public void saveConfirmationToken(ConfirmationToken confirmationToken);
    public Optional<ConfirmationToken> getToken(String token);
}
