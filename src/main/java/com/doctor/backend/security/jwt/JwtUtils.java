package com.doctor.backend.security.jwt;

import com.doctor.backend.security.service.UserDetailsImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtUtils {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;


    public JwtUtils(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    public Claims decodeJwt(String token) {
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build().parseClaimsJws(token);
        return jws.getBody();

    }

    public String generateToken(UserDetailsImp user) {
        var expiration = java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays()));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .claim("PatientId", user.getPatientId())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }
}
