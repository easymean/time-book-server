package com.spring.timebook.auth.oauth;

import com.spring.timebook.config.OAuthKakaoConfig;
import com.spring.timebook.config.OAuthNaverConfig;
import com.spring.timebook.user.CreateUserDto;
import com.spring.timebook.user.User;
import com.spring.timebook.user.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthService {

    private final UserService userService;
    private final OAuthNaverConfig oAuthNaverConfig;
    private final OAuthKakaoConfig oAuthKakaoConfig;

    private final Map<OAuthProvider, OAuthProviderService> providerMap = new HashMap<>();

    public OAuthService(UserService userService, OAuthNaverConfig oAuthNaverConfig, OAuthKakaoConfig oAuthKakaoConfig){
        this.userService = userService;
        this.oAuthNaverConfig = oAuthNaverConfig;
        this.oAuthKakaoConfig = oAuthKakaoConfig;
        init();
    }

    private void init(){
        providerMap.put(OAuthProvider.NAVER, new OAuthNaverService(oAuthNaverConfig));
        providerMap.put(OAuthProvider.KAKAO, new OAuthKakaoService(oAuthKakaoConfig));
    }

    private OAuthProviderService getOAuthProvider(OAuthProvider type){
        return providerMap.get(type);
    }

    public String redirect(OAuthProvider type){
        OAuthProviderService oAuthProviderService = getOAuthProvider(type);
        return oAuthProviderService.redirect();
    }

    public boolean loginByOAuth(OAuthProvider type, Map<String, String> info) {
        OAuthProviderService oAuthProviderService = getOAuthProvider(type);
        OAuthUser oAuthUser = oAuthProviderService.process(info);
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

    public boolean logout(OAuthProvider type){
        OAuthProviderService oAuthProviderService = getOAuthProvider(type);
        return true;
    }

}
