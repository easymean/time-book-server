package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import com.spring.timebook.user.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthAdapter {

    private Map<String, OAuthService> serviceMap = new HashMap<>();

    private final UserService userService;

    public OAuthAdapter(UserService userService){
        this.userService = userService;

        initServiceMap();
    }


    private void initServiceMap(){
        serviceMap.put("naver", new OAuthNaverService());
    }

    private OAuthService getOAuthService(String type){
        return serviceMap.get(type);
    }

    public void loginByOAuth(String type) {

        OAuthService oAuthService = getOAuthService(type);

        User loginUser = oAuthService.process();
        Long id = userService.getUserByEmail(loginUser.getEmail())
                .orElseGet(() -> userService.createUser()).getId();

        // 토큰 발급

    }

}
