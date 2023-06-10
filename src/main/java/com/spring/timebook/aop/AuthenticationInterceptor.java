package com.spring.timebook.aop;

import com.spring.timebook.auth.AuthService;
import com.spring.timebook.auth.AuthUser;
import com.spring.timebook.auth.token.TokenProvider;
import com.spring.timebook.auth.permission.Permission;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private final AuthService authService;

    public AuthenticationInterceptor(TokenProvider tokenProvider, AuthService authService) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        Permission permission = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
        if(permission == null){
            return true;
        }

        try {
            String token = extractTokenFromHeader(request);
            String userId = tokenProvider.getUserIdFromToken(token);
            AuthUser user = authService.loadUserById(userId);
            request.setAttribute("user", user);


            return true;
        }catch (Exception e){
            throw new InterceptorException(e.getMessage());
        }

    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        try {
            return request.getHeader("Authorization").split(" ")[1];
        }catch(Exception e){
            throw new InterceptorException("Cannot extract token from header");
        }
    }
}
