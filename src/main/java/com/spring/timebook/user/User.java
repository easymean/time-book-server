package com.spring.timebook.user;

import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.tag.Tag;
import com.spring.timebook.time.Time;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
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
