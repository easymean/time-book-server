package com.spring.timebook.tag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @ResponseBody
    public String getTagsByType(@RequestParam SpendingType type){
        List<Tag> tags = tagService.getTagsByType(type);
        return "";
    }

    @PostMapping
    @ResponseBody
    public String createTag(){
        return "";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String updateTag(@PathVariable Long id){
        return "";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteTag(@PathVariable Long id){
        return "";
    }
}
