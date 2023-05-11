package com.spring.timebook.time;

import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.tag.SpendingType;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalTime;

public class Time extends BaseEntity {
    private Long id;

    @Setter
    private String description;

    private final Long userId;

    @Setter
    private Long tagId;

    @Setter
    private SpendingType spendingType;

    @Setter
    private LocalTime startAt;

    @Setter
    private LocalTime endAt;

    @Builder
    public Time(String description, SpendingType spendingType, LocalTime startAt, LocalTime endAt, Long userId) {
        super();
        this.description = description;
        this.spendingType = spendingType;
        this.startAt = startAt;
        this.endAt = endAt;
        this.userId = userId;
    }



}
