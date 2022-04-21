package com.example.confirmemail.validator;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String arg0) {
        // Todo regex with email
        return true;
    }    
}
