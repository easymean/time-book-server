package com.spring.timebook.aop;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.servlet.HandlerInterceptor;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String token = request.getHeader("Authorization");
        if(token == null){
            throw new AuthenticationException("JWT is null");
        }

        try {

            return true;

        }catch(JwtException e){
            throw new AuthenticationException("JWT is invalid");
        }
    }
}
