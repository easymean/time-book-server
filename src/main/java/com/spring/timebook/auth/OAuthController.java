package com.spring.timebook.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oauth2")
public class OAuthController {

    private final OAuthAdapter oAuthAdapter;

    public OAuthController(OAuthAdapter oAuthAdapter) {
        this.oAuthAdapter = oAuthAdapter;
    }

    @PostMapping("/naver")
    @ResponseBody
    public String loginByNaver(){
        oAuthAdapter.loginByOAuth("naver");
        return "";
    }

    @PostMapping("/google")
    @ResponseBody
    public String loginByGoogle(){
        oAuthAdapter.loginByOAuth("google");
        return "";
    }

    @PostMapping("/kakao")
    @ResponseBody
    public String loginByKakao(){
        oAuthAdapter.loginByOAuth("kakao");
        return "";
    }
}
