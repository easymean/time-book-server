package com.spring.timebook.common;

import java.time.Instant;

public class BaseEntity {
    private final Instant createdAt;
    private Instant updatedAt;
    private boolean isDeleted;

    public BaseEntity(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.isDeleted = false;
    }
}
