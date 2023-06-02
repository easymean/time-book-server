package com.spring.timebook.auth.exception;

public class ParseException extends RuntimeException {

    private String message;
    public ParseException(String message){
        super(message);
        this.message = message;
    }
}
