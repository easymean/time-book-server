package com.spring.timebook.tag;

import com.spring.timebook.common.BaseEntity;
import lombok.Builder;
import lombok.Setter;

public class Tag extends BaseEntity {

    private Long id;

    @Setter
    private String title;

    @Setter
    private SpendingType spendingType;

    private final Long userId;


    @Builder
    public Tag(String title, SpendingType spendingType, Long userId) {
        super();
        this.title = title;
        this.spendingType = spendingType;
        this.userId = userId;
    }
}
