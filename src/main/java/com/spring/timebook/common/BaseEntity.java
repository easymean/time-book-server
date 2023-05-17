package com.spring.timebook.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
