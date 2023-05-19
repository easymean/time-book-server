package com.spring.timebook.user;

import com.spring.timebook.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

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


}
