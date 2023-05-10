package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public @ResponseBody String login(){
        return "토큰";
    }

    @GetMapping("/logout")
    public @ResponseBody boolean logout(){
        return true;
    }

    @PostMapping("/signup")
    public @ResponseBody User signUp(){
        return authService.join();
    }
}
