package com.spring.timebook.auth.jwt;

import com.spring.timebook.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider implements TokenProvider {

    private static final String AUTHORITIES_KEY = "email";
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000L;
    private final Key key;

    public JwtProvider(@Value("${jwt.secret}") String secretKey){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String createToken(User user) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(user.getId().toString()) // 구분자
                .claim(AUTHORITIES_KEY, user.getEmail())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    @Override
    public String getUserIdFromToken(String token){
        if(validateToken(token)){
            return parseClaims(token).getSubject();
        }else {
            throw new JwtException("Can not get userId from token");
        }
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch(UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException | ExpiredJwtException e){
            // 에러 처리
            // 유효하지 않은 토큰입니다.
            e.printStackTrace();
        }

        return false;
    }

    private Claims parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();
    }
}
