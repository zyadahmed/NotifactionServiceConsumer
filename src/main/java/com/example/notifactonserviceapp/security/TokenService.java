package com.example.notifactonserviceapp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class TokenService {
    private SecretKey key;

    @Value("${key}")
    private String secretKey;

    @PostConstruct
    public void buildKey() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String validateTokenAndGetUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String id =  claims.get("id").toString(); // Assuming the subject is the user ID
            System.out.println(id);
            return id;
        } catch (Exception e) {
            return null;
        }
    }
}
