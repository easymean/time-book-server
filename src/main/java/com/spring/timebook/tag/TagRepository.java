package com.spring.timebook.tag;

import java.util.List;

public interface TagRepository {
    Tag save(Tag tag);
    List<Tag> findAllBySpendingType(SpendingType type);
}
