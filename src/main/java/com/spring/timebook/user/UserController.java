package com.spring.timebook.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}/username")
    @ResponseBody
    public String changeUsername(@PathVariable Long id){
        return "";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUserInfo(@PathVariable Long id){
        return "getUser";
    }

}
