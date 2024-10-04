package com.rituraj.notification.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class VerificationCodeService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateVerificationCode(String email) {
//        Expiration for 5 minutes in milliseconds
        long expirationDate = 5 * 60 * 1000;
        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .compact();
    }

    public boolean checkVerificationCode(String email, String verificationCode) {
        String decodedEmail = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(verificationCode)
                .getBody()
                .getSubject();
        return email.equals(decodedEmail);
    }

}
