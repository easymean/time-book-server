package com.spring.timebook.auth;

import com.spring.timebook.auth.oauth.OAuthService;
import com.spring.timebook.auth.oauth.OAuthProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;

    public OAuthController(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/naver")
    @ResponseBody
    public String redirectToNaver(){
        return oAuthService.redirect(OAuthProvider.NAVER);
    }

    @GetMapping("/callback/naver")
    @ResponseBody
    public String callbackByNaver(@RequestParam String code, @RequestParam String state){
        Map<String, String> info = new HashMap<>();
        info.put("code",code);
        info.put("state", state);
        return oAuthService.loginByOAuth(OAuthProvider.NAVER, info);
    }

    @PostMapping("/google")
    @ResponseBody
    public String loginByGoogle(){

        return oAuthService.redirect(OAuthProvider.GOOGLE);
    }

    @GetMapping("/kakao")
    @ResponseBody
    public String redirectToKakao(){
        return oAuthService.redirect(OAuthProvider.KAKAO);
    }

    @GetMapping("/callback/kakao")
    @ResponseBody
    public String callbackByKakao(@RequestParam String code){
        Map<String, String> info = new HashMap<>();
        info.put("code",code);
        return oAuthService.loginByOAuth(OAuthProvider.KAKAO, info);
    }

}
