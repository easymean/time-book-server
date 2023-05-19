package com.spring.timebook.aop;

import com.spring.timebook.auth.TokenProvider;
import com.spring.timebook.auth.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    public AuthenticationInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        try {
            String token = extractTokenFromHeader(request);
            String userId = tokenProvider.getUserIdFromToken(token);
            request.setAttribute("userId", userId);

            return true;
        }catch (Exception e){
            throw new InterceptorException();
        }

    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        try {
            return request.getHeader("Authorization").split(" ")[1];
        }catch(Exception e){
            throw new AuthenticationException("Cannot extract token from header");
        }
    }
}
