package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtProvider implements TokenProvider {

    private static final String AUTHORITIES_KEY= "username";
    private static final long JWT_EXPIRATION_MS = 604800000;
    private final byte[] key;


    public JwtProvider(String secretKey){
        key = secretKey.getBytes();
    }

    @Override
    public String createToken(User user) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(AUTHORITIES_KEY, user.getUsername())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(validity)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        }catch(ExpiredJwtException e){
            // 에러 처리
            e.printStackTrace();
        }catch (Exception e){
            // 에러 처리
            e.printStackTrace();
        }

        return false;
    }
}
