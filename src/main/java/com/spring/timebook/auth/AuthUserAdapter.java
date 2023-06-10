package com.spring.timebook.auth;

import com.spring.timebook.user.User;

public class AuthUserAdapter extends AuthUser {
    private User user;

    public AuthUserAdapter(User user) {
        super(user.getId(), user.getUsername(), user.getPermissionRole());
        this.user = user;

    }

    public User getUser(){
        return this.user;
    }
}
