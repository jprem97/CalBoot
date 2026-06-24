package com.first.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.first.demo.entity.User;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.function.Function;

@Service
public class jwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    private SecretKey getSigningKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

      public String generateAccessToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                // .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(getSigningKey())
                .compact();
    }

      public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                // .claim("userId", user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7))
                .signWith(getSigningKey())
                .compact();
    }

   public String  extractEmail(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

//     public <T> T extractClaim(
//             String token,
//             Function<Claims,T> resolver) {

//         Claims claims =
//                 extractAllClaims(token);

//         return resolver.apply(claims);
//     }

//     public Claims extractAllClaims(
//             String token) {

//         return Jwts.parser()
//                 .verifyWith(getSigningKey())
//                 .build()
//                 .parseSignedClaims(token)
//                 .getPayload();
//     }

//     public boolean isTokenValid(
//             String token,
//             UserDetails userDetails) {

//         String email =
//                 extractEmail(token);

//         return email.equals(
//                 userDetails.getUsername())
//                 && !isTokenExpired(token);
//     }

//     private boolean isTokenExpired(
//             String token) {

//         return extractAllClaims(token)
//                 .getExpiration()
//                 .before(new Date());
//     }
}