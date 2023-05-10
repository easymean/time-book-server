package com.spring.timebook.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/user")
public class UserController {

    public @ResponseBody String getUserById(){
        return "getUser";
    }


    public @ResponseBody String getUsers(){
        return "getUsers";
    }


}
