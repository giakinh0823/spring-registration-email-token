package com.example.confirmemail.service;

import com.example.confirmemail.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    public String signUp(User user);
    public void enableUser(String email);
}
