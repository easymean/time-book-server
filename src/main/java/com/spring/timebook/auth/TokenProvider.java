package com.spring.timebook.auth;

import com.spring.timebook.user.User;

public interface TokenProvider {

    String createToken(User user);
    boolean validateToken(String token);
}
