package com.spring.timebook.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    @Override
    User save(User user);

    @Override
    Optional<User> findByEmail(String email);
}
