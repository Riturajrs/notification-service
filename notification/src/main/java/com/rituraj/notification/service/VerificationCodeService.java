package com.rituraj.notification.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class VerificationCodeService {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateVerificationCode(String email) {
//        Expiration for 5 minutes in milliseconds
        long expirationDate = 5 * 60 * 1000;
        return Jwts
                .builder()
                .setSubject(email+"verified")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String decodeVerificationCode(String verificationCode) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(verificationCode)
                .getBody()
                .getSubject();
    }

}
