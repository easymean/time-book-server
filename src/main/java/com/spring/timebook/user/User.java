package com.spring.timebook.user;

import com.spring.timebook.auth.permission.PermissionRole;
import com.spring.timebook.common.BaseEntity;
import com.spring.timebook.tag.Tag;
import com.spring.timebook.time.Time;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor
@ToString
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Setter @Getter
    @Column(nullable = false)
    private String username;

    @Setter
    private int level;

    @Setter
    private long savedTime;

    @Setter @Getter
    @Enumerated(EnumType.STRING)
    private PermissionRole permissionRole;

    @Setter
    private String snsId;

    @Setter
    private String snsType;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @Getter
    private List<Time> times = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @Getter
    private List<Tag> tags = new ArrayList<>();

    @Builder
    public User(String email, String username) {
        super();
        this.email = email;
        this.username = username;
        this.level = 1;
        this.savedTime = 0;
        this.permissionRole = PermissionRole.MEMBER;
    }
}
