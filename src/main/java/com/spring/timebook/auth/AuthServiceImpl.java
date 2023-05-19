package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import com.spring.timebook.user.UserRepository;
import com.spring.timebook.user.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User join() {
        return null;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public AuthUser loadUserById(String id) {
        User user = userRepository.findOneById(Long.parseLong(id))
                .orElseThrow(()-> new UserNotFoundException("${id}에 해당하는 유저가 존재하지 않습니다."));

        return new AuthUserAdapter(user);

    }
}
