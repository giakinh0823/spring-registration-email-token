package com.example.confirmemail.service;

import com.example.confirmemail.model.RegistrationRequest;

public interface RegistrationService {
    public String register(RegistrationRequest request);
    public String confirmToken(String token);
}
