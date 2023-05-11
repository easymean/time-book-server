package com.spring.timebook.tag;

import com.spring.timebook.common.BaseEntity;

public class Tag extends BaseEntity {

    private Long id;
    private String title;
    private SpendingType spendingType;
    private Long userId;

    public Tag(String title, SpendingType spendingType, Long userId) {
        super();
        this.title = title;
        this.spendingType = spendingType;
        this.userId = userId;
    }
}
