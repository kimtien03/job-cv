//package com.example.bejobapplication.Utils;
//
//import com.example.bejobapplication.Entity.User;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Slf4j
//public class JwtUtils {
//    protected static String JWT_SECRET = "K1YJD9Mt3V3wklqFpuYQXZhKSMdh6UZyLBb/nvXKBk1Lw3yXtRYTcpLqwOa8ACsc";
//    protected static long JWT_EXPIRATION = 86400000; // 3 ngày
//
//    @Autowired
//    public void setEnvironment(Environment environment) {
//        JWT_SECRET = environment.getProperty("jwt.signerKey", JWT_SECRET);
//        JWT_EXPIRATION = Long.parseLong(environment.getProperty("jwt.expirationTime", String.valueOf(JWT_EXPIRATION)));
//    }
//
//    public static String generateToken(User user) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
//        return Jwts.builder()
//                .setSubject(user.getEmail())
////                .claim("role", user.getRole())
//                .claim("id", user.getId())
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//    }
//
//    public static void addTokenToCookie(HttpServletResponse response, String token) {
//        Cookie cookie = new Cookie("auth_token", token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true); // Bảo mật thêm
//        cookie.setMaxAge((int) (JWT_EXPIRATION / 1000)); // đổi sang giây
//        response.addCookie(cookie);
//    }
//
//    public static String getUserEmailFromJWT(String token) {
//        try {
//            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
//            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            return claims.getSubject();
//        } catch (JwtException | IllegalArgumentException e) {
//            log.error("Invalid or expired JWT token", e);
//            return null;
//        }
//    }
//
//    public static String getUserRoleFromJWT(String token) {
//        try {
//            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
//            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            return claims.get("role", String.class);
//        } catch (JwtException | IllegalArgumentException e) {
//            log.error("Invalid or expired JWT token", e);
//            return null;
//        }
//    }
//
//    public static boolean validateToken(String authToken) {
//        try {
//            SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
//            return true;
//        } catch (JwtException | IllegalArgumentException ex) {
//            log.error("JWT validation error: {}", ex.getMessage());
//            return false;
//        }
//    }
//}
