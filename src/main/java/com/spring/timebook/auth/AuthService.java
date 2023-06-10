package com.spring.timebook.auth;

public interface AuthService {
    boolean logout();

    AuthUser loadUserById(String id);
}
