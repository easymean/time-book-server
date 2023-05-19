package com.spring.timebook.aop;

import com.spring.timebook.auth.TokenProvider;
import com.spring.timebook.user.User;
import com.spring.timebook.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    public AuthenticationInterceptor(TokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
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
            User user = userService.loadUserById(userId);

            request.setAttribute("userId", user.getId());
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
