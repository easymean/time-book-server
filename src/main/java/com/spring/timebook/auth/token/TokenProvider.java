package com.spring.timebook.auth.token;

import com.spring.timebook.user.User;

public interface TokenProvider {
    String createToken(User user);
    String getUserIdFromToken(String token);
}
