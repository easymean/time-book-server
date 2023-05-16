package com.spring.timebook.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/oauth2")
public class OAuthController {

    @PostMapping("/login/naver")
    @ResponseBody
    public String naverCbLogin(){
        return "";
    }
}
