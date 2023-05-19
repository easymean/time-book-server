package com.spring.timebook.user;

public interface UserService {
    void changeUsername();
    User getUserInfo();
    User loadUserById(final String id);

}
