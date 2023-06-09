package com.spring.timebook.user;

import java.util.Optional;

public interface UserRepository{
    User save(User user);
    Optional<User> findByEmail(String email);
}
