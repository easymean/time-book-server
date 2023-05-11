package com.spring.timebook.user;

import com.spring.timebook.common.BaseEntity;
import lombok.Builder;
import lombok.Setter;

public class User extends BaseEntity {
    private Long id;
    private final String email;

    @Setter
    private String username;

    @Setter
    private int level;

    @Setter
    private long savedTime;

    @Builder
    public User(String email, String username) {
        super();
        this.email = email;
        this.username = username;
        this.level = 1;
        this.savedTime = 0;
    }
}
