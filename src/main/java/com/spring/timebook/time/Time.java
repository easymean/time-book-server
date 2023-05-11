package com.spring.timebook.time;

import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.tag.SpendingType;

import java.time.LocalTime;

public class Time extends BaseEntity {
    private Long id;
    private String description;
    private Long userId;
    private Long tagId;
    private SpendingType spendingType;

    private LocalTime startAt;
    private LocalTime endAt;

    public Time(String description, SpendingType spendingType, LocalTime startAt, LocalTime endAt) {
        super();
        this.description = description;
        this.spendingType = spendingType;
        this.startAt = startAt;
        this.endAt = endAt;
    }



}
