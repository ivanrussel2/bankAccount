package com.exatIt.kata.exaltbank.domain.user;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
