package com.thalesbensi.CoursesManagementAPI.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.service}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("CourseManagementAPI")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("JWT generation failed: ", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("CourseManagementAPI")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
