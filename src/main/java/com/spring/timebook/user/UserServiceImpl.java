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

    @Override
    public User loadUserById(String id) {
        return userRepository.findOneById(Long.parseLong(id))
                .orElseThrow(() -> new UserNotFoundException("${id}에 해당하는 유저를 찾을 수 없습니다."));
    }
}
