package br.com.adeptsd.product;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class JWTHandler {

    private byte[] key;

    public JWTHandler(String key) {
        this.key = DatatypeConverter.parseBase64Binary(key);
    }

    public String build(String subject, LocalDateTime expiration) {
        return Jwts.builder().setHeaderParam("type", "JWT")
                .setSubject(subject)
                .setExpiration(Date.from(expiration.toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
