package com.zefernando.PaymentPix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.zefernando.PaymentPix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm alg = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("auth")
                    .withSubject(user.getEmail()).withExpiresAt(Expiration())
                    .sign(alg);

            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("The token was not generated", exception);
        }
    }

    private Instant Expiration(){
        return LocalDateTime.now().plusMinutes(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
