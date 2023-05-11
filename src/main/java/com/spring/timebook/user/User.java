package com.spring.timebook.user;

import com.spring.timebook.common.BaseEntity;

public class User extends BaseEntity {
    private Long id;
    private String email;
    private String username;
    private int level;
    private long savedTime;

    public User(String email, String username) {
        super();
        this.email = email;
        this.username = username;
        this.level = 1;
        this.savedTime = 0;
    }
}
