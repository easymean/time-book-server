package com.spring.timebook.auth;

import com.spring.timebook.user.User;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtProvider implements TokenProvider {

    private static final String AUTHORITIES_KEY= "username";
    private static final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000L;
    private final byte[] key;


    public JwtProvider(String secretKey){
        key = secretKey.getBytes();
    }

    @Override
    public String createToken(User user) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(user.getId().toString()) // 구분자
                .claim(AUTHORITIES_KEY, user.getUsername())
                .signWith(SignatureAlgorithm.HS256, key)
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
            Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token);
            return true;
        } catch(ExpiredJwtException e){
            // 에러 처리
            // 토큰이 만료되었습니다.
            e.printStackTrace();
        } catch(UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            // 에러 처리
            // 유효하지 않은 토큰입니다.
            e.printStackTrace();
        }
        return false;
    }

    private Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
