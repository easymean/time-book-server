package com.spring.timebook.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {

    private String email;
    private String username;
    private String snsId;
    private String snsType;

    @Builder
    public CreateUserDto(String email, String username, String snsId, String snsType){
        this.email = email;
        this.username = username;
        this.snsId = snsId;
        this.snsType = snsType;
    }
}
