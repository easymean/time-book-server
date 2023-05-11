package com.spring.timebook.tag;

import java.util.List;

public interface TagRepository {
    Tag save(Tag tag);
    List<Tag> findAll(SpendingType type);
    Tag update(Tag tag);
    void delete(Long id);
}
