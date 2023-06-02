package com.spring.timebook.user;

import java.util.Optional;

public interface UserService {

    void changeUsername();

    User getUserInfo();

    Optional<User> getUserByEmail(String email);

    User createUser();

}
