package com.spring.timebook.tag;

import java.util.List;

public interface TagService {

    List<Tag> getTagsByType(SpendingType type);
    Tag createTag();
    Tag updateTag();
    void deleteTag();
}
