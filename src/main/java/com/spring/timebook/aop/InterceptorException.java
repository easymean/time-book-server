package com.spring.timebook.aop;

public class InterceptorException extends RuntimeException{
    String message;

    public InterceptorException(String message) {
        super();
        this.message = message;
    }
}
