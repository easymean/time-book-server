package com.spring.timebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TimeController {

    @GetMapping("/time")
    public @ResponseBody  String getTime() {
        return "Hello World!";
    }
}
