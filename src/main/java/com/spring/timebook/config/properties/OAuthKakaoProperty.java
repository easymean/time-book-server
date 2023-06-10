package com.spring.timebook.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "login.kakao")
@Getter
@Setter
public class OAuthKakaoProperty {
    private String apiKey;
    private String redirectUri;
}
