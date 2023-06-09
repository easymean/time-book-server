package com.spring.timebook.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthAdapter oAuthAdapter;

    public OAuthController(OAuthAdapter oAuthAdapter) {
        this.oAuthAdapter = oAuthAdapter;
    }

    @GetMapping("/naver")
    @ResponseBody
    public String redirectToNaver(){
        return oAuthAdapter.redirect(OAuthProvider.NAVER);
    }

    @GetMapping("/callback/naver")
    @ResponseBody
    public boolean callbackByNaver(@RequestParam String code, @RequestParam String state){
        Map<String, String> info = new HashMap<>();
        info.put("code",code);
        info.put("state", state);
        return oAuthAdapter.loginByOAuth(OAuthProvider.NAVER, info);
    }

    @PostMapping("/google")
    @ResponseBody
    public String loginByGoogle(){

        return oAuthAdapter.redirect(OAuthProvider.GOOGLE);
    }

    @PostMapping("/kakao")
    @ResponseBody
    public String redirectToKakao(){
        return oAuthAdapter.redirect(OAuthProvider.KAKAO);
    }
}
