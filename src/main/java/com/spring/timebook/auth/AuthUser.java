package com.spring.timebook.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthUser {
    Long id;
    String username;
    PermissionRole permission;

    @Builder
    public AuthUser(Long id, String username, PermissionRole permission){
        this.id = id;
        this.username = username;
        this.permission = permission;
    }
}