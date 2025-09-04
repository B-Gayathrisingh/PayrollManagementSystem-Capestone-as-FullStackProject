package com.example.AdminService.AdminService.service;

import com.example.AdminService.AdminService.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

//@Component
//public class JwtService {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private String jwtExpiration;
//
//    private final String SECRET = "MyJwtSecretKeyForEncryptionMyJwtSecretKeyForEncryption"; // at least 32 chars
//    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    private Key getKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
////    public String generateToken(User user) {
////        Map<String, Object> claims = new HashMap<>();
////        claims.put("userId", user.getUserid());
////        claims.put("roles", user.getRoles().stream()
////                .map(Role::getName)   // assuming Role has getName()
////                .toList());
////
////
////        return Jwts.builder()
////                .setClaims(claims)
////                .setSubject(user.getEmail())
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
////                .signWith(getKey(), SignatureAlgorithm.HS256)
////                .compact();
////    }
////        // ✅ Extract userId
////    public String extractUserId(String token) {
////        return (String) getClaims(token).get("userId");
////    }
////public String generateToken(User user) {
////    return Jwts.builder()
////            .setSubject(user.getEmail())
////            .claim("roles", user.getRoles().stream()
////                    .map(Role -> Role.getName())
////                    .collect(Collectors.toList()))
////            .setIssuedAt(new Date())
////            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
////            .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
////            .compact();
////}
//
//    public String generateToken(String email, Set<Role> roles) {
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
////        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("roles", roles)
//                .signWith(key)
//                .compact();
//    }
//
//
//    // ✅ Extract roles
//    public Object extractRoles(String token) {
//        return getClaims(token).get("roles");
//    }
//
//    private Claims getClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public Claims parse(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//}
@Service
public class JwtService {

    @Value("${jwt.secret}") // e.g., MySuperSecretKeyForJWTs123456
    private String secret;

    @Value("${jwt.expiration}") // e.g., 86400000 (1 day in ms)
    private long jwtExpiration;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, Set<Role> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles.stream().map(Role::getName).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getKey())
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return parse(token).getSubject();
    }

    public Object extractRoles(String token) {
        return parse(token).get("roles");
    }
}