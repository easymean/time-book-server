package com.spring.timebook.config;

import com.spring.timebook.aop.AuthenticationInterceptor;
import com.spring.timebook.auth.token.JwtProvider;
import com.spring.timebook.auth.token.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("{jwt.secret}")
    private String tokenSecret;

    private final AuthenticationInterceptor authenticationInterceptor;
    public WebMvcConfig(AuthenticationInterceptor authenticationInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor);
    }

    @Bean
    public TokenProvider tokenProvider(){
        return new JwtProvider(tokenSecret);
    }
}
