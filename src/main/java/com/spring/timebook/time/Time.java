package com.spring.timebook.time;

import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.tag.SpendingType;
import com.spring.timebook.tag.Tag;
import com.spring.timebook.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="time")
@NoArgsConstructor
public class Time extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String description;

    private final Long userId;

    @Setter
    private Long tagId;

    @Setter
    private SpendingType spendingType;

    @Setter
    @Temporal(TemporalType.TIME)
    private LocalTime startAt;

    @Setter
    @Temporal(TemporalType.TIME)
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
