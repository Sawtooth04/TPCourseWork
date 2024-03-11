package com.sawtooth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public final class JwtUtils {
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.expiration}")
    private int expiration;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Date issued = new Date(), expired = new Date(issued.getTime() + expiration);
        return Jwts.builder()
                .claims(claims)
                .issuedAt(issued)
                .expiration(expired)
                .subject(userDetails.getUsername())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).compact();
    }

    public Claims getClaims(String token) {
         JwtParser parser = Jwts.parser().verifyWith((SecretKey) getSigningKey()).build();

         return parser.parseSignedClaims(token).getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
