package com.spring.timebook.auth;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OAuthUser {

    private String email;
    private String nickname;
    private String snsId;
    private OAuthProvider snsType;

    @Builder
    public OAuthUser(String email, String nickname, String snsId, OAuthProvider snsType) {
        this.email = email;
        this.nickname = nickname;
        this.snsId = snsId;
        this.snsType = snsType;
    }
}
