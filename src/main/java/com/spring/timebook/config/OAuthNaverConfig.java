package com.spring.timebook.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "login.naver")
@Getter
@Setter
public class OAuthNaverConfig {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
