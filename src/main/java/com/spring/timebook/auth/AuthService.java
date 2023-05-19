package com.spring.timebook.auth;

import com.spring.timebook.user.User;

public interface AuthService {
    User join();
    String login();
    void logout();

    AuthUser loadUserById(String id);
}
