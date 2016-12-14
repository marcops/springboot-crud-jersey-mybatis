package br.com.adeptsd.product;

import io.jsonwebtoken.Claims;

import java.time.LocalDateTime;

import br.com.adeptsd.product.entity.User;

public class Authorization {

    private final String key;
    private final Integer expiresInSeconds;

    public Authorization(String key, Integer expiresInSeconds) {
        this.key = key;
        this.expiresInSeconds = expiresInSeconds;
    }

    public String authorize(String token) {
        Claims claims = new JWTHandler(key).parse(token);
        return claims.getSubject();
    }

    public String buildToken(User user) {
        return buildToken(user, expiresInSeconds);
    }
    
    public String buildToken(User user, Integer expiresInSeconds) {
        LocalDateTime expirationDate = LocalDateTime.now().plusSeconds(expiresInSeconds);
        return new JWTHandler(key).build(user.toString(), expirationDate);
    }

}