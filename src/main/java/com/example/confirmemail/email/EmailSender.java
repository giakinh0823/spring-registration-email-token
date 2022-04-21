package com.example.confirmemail.email;

public interface EmailSender {
    void send(String subject, String to, String email) throws IllegalArgumentException;
}
