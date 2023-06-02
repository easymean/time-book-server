package com.spring.timebook.auth;

import com.spring.timebook.user.User;

public interface OAuthService {
    void redirect();
    User process();
}
