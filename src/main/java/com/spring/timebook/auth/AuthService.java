package com.spring.timebook.auth;

import com.spring.timebook.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean logout() {
        return true;
    }

}
