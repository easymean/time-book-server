package com.spring.timebook.time;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time")
public class TimeController {

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping
    @ResponseBody
    public String getTimeListByDay(@RequestParam int month, @RequestParam int day) {
        return "Hello World!";
    }

    @PostMapping
    @ResponseBody
    public String createTime(){
        return "";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updateTime(@PathVariable Long id){
        return "Hello world";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteTime(@PathVariable Long id){
        return "";
    }
}
