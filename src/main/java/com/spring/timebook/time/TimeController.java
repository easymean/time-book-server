package com.spring.timebook.time;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/time")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping()
    public @ResponseBody  String getTime() {
        return "Hello World!";
    }

    @GetMapping("/day")
    public @ResponseBody String getTimeListByDay(){
        return "Hello world";
    }
}
