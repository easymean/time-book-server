package com.spring.timebook.tag;

import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.time.Time;
import com.spring.timebook.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tag")
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    @Enumerated(EnumType.STRING)
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
