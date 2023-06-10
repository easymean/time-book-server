package com.spring.timebook.auth.oauth;

import com.spring.timebook.auth.jwt.TokenProvider;
import com.spring.timebook.config.properties.OAuthKakaoProperty;
import com.spring.timebook.config.properties.OAuthNaverProperty;
import com.spring.timebook.user.CreateUserDto;
import com.spring.timebook.user.User;
import com.spring.timebook.user.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthService {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final OAuthNaverProperty oAuthNaverProperty;
    private final OAuthKakaoProperty oAuthKakaoProperty;

    private final Map<OAuthProvider, OAuthProviderService> providerMap = new HashMap<>();

    public OAuthService(UserService userService, TokenProvider tokenProvider, OAuthNaverProperty oAuthNaverProperty, OAuthKakaoProperty oAuthKakaoProperty){
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.oAuthNaverProperty = oAuthNaverProperty;
        this.oAuthKakaoProperty = oAuthKakaoProperty;
        init();
    }

    private void init(){
        providerMap.put(OAuthProvider.NAVER, new OAuthNaverService(oAuthNaverProperty));
        providerMap.put(OAuthProvider.KAKAO, new OAuthKakaoService(oAuthKakaoProperty));
    }

    private OAuthProviderService getOAuthProvider(OAuthProvider type){
        return providerMap.get(type);
    }

    public String redirect(OAuthProvider type){
        OAuthProviderService oAuthProviderService = getOAuthProvider(type);
        return oAuthProviderService.redirect();
    }

    public String loginByOAuth(OAuthProvider type, Map<String, String> info) {
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

        return tokenProvider.createToken(user);
    }

    public boolean logout(OAuthProvider type){
        OAuthProviderService oAuthProviderService = getOAuthProvider(type);
        return true;
    }

}
