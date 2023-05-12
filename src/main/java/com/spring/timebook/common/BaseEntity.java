package com.spring.timebook.common;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

public class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;

    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Instant updatedAt;

    @Setter
    @Getter
    private boolean isDeleted;

    public BaseEntity(){
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.isDeleted = false;
    }
}
