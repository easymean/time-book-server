package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import org.springframework.stereotype.Component;

@Component
public class OAuthGoogleService implements AuthService{

    @Override
    public User join() {
        return null;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public void logout() {

    }
}
