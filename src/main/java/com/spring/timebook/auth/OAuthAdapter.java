package com.spring.timebook.auth;

import com.spring.timebook.config.OAuthKakaoConfig;
import com.spring.timebook.config.OAuthNaverConfig;
import com.spring.timebook.user.CreateUserDto;
import com.spring.timebook.user.User;
import com.spring.timebook.user.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthAdapter {

    private final UserService userService;
    private final OAuthNaverConfig oAuthNaverConfig;
    private final OAuthKakaoConfig oAuthKakaoConfig;

    private final Map<OAuthProvider, OAuthLoginService> loginServiceMap = new HashMap<>();

    public OAuthAdapter(UserService userService, OAuthNaverConfig oAuthNaverConfig, OAuthKakaoConfig oAuthKakaoConfig){
        this.userService = userService;
        this.oAuthNaverConfig = oAuthNaverConfig;
        this.oAuthKakaoConfig = oAuthKakaoConfig;

        init();
    }

    private void init(){
        loginServiceMap.put(OAuthProvider.NAVER, new OAuthNaverLoginService(oAuthNaverConfig));
        loginServiceMap.put(OAuthProvider.KAKAO, new OAuthKakaoLoginService(oAuthKakaoConfig));
    }

    private OAuthLoginService getOAuthService(OAuthProvider type){
        return loginServiceMap.get(type);
    }

    public String redirect(OAuthProvider type){
        OAuthLoginService oAuthLoginService = getOAuthService(type);
        return oAuthLoginService.redirect();
    }

    public boolean loginByOAuth(OAuthProvider type, Map<String, String> info) {
        OAuthLoginService oAuthLoginService = getOAuthService(type);
        OAuthUser oAuthUser = oAuthLoginService.process(info);
        User user = userService.getUserByEmail(oAuthUser.getEmail())
                .orElseGet(() -> userService.createUser(
                        CreateUserDto.builder()
                                .email(oAuthUser.getEmail())
                                .username(oAuthUser.getNickname())
                                .snsType(oAuthUser.getSnsType().name())
                                .build()
                ));

        Long id = user.getId();
        // 토큰 발급
        return true;
    }

}
