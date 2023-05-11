package com.spring.timebook.tag;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getTagsByType(SpendingType type) {
        return null;
    }

    @Override
    public Tag createTag() {
        return null;
    }

    @Override
    public Tag updateTag() {
        return null;
    }

    @Override
    public void deleteTag() {

    }
}
