package com.spring.timebook.user.exception;

public class UserNotFoundException extends RuntimeException{
    String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
