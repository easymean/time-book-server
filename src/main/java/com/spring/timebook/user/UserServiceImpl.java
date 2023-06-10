package com.spring.timebook.user;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void changeUsername() {
    }

    @Override
    public User getUserInfo() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(CreateUserDto userDto) {
        User user =  User.builder().
                email(userDto.getEmail())
                .username(userDto.getUsername())
                .build();
        userRepository.save(user);
        return user;
    }
}
