package com.example.flutter_gpt_project_backend.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.zaxxer.hikari.util.ClockSource.plusMillis;

@Component

public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private final Key key;

    private final long accessTokenExpTime;

    public JwtUtil(@Value("${jwtSecretKey}") final String jwtSecretKey,
                   @Value("${accessTokenExpTime}") final long accessTokenExpTime) {
        Key jwtSecretKeys = Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        key = jwtSecretKeys;
        this.accessTokenExpTime = accessTokenExpTime;

    }

    public String creatAccessToken(CustomUserInfoDTO dto){
        System.out.println("JwtUtil creatAccessToken called");
        return createToken(dto, accessTokenExpTime);
    }

    private String createToken(CustomUserInfoDTO dto, long expTime){
        Claims claims = Jwts.claims();
        claims.put("id", dto.getId());
        claims.put("userId", dto.getUserId());
        claims.put("userPw", dto.getUserPw());

        claims.put("email", dto.getEmail());
        claims.put("name", dto.getName());
        claims.put("role", dto.getRole());
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);
        System.out.println("claims"+ claims.getId() + claims.get("id") + " "+ claims.get("userId") + " "+claims.get("userPw") + " "+claims.get("email") +" "+ claims.get("name") + " "+claims.get("role"));



        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(tokenValidity.toInstant().plusMillis(expTime)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserId(String token) {
        return parseClaims(token).get("id", String.class);
    }



    public boolean isValidToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            logger.info("Invalid JWT", e);
        } catch (ExpiredJwtException e) {
            logger.info("Expired JWT", e);
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT", e);
        } catch (IllegalArgumentException e) {
            logger.info("JWT claims string is empty", e);
        }
        return false;
    }

    public Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken).getBody();

        }catch(ExpiredJwtException e) {
            return e.getClaims();
        }

    }
}
