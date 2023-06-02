package com.spring.timebook.user;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Override
    public void changeUsername() {
    }

    @Override
    public User getUserInfo() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User createUser() {
        return null;
    }
}
