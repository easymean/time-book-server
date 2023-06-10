package com.spring.timebook.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findOneById(Long id);
}
