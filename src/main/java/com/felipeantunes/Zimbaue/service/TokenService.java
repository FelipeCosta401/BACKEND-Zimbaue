package com.felipeantunes.Zimbaue.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.felipeantunes.Zimbaue.model.entity.User;
import com.felipeantunes.Zimbaue.service.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret-key");
            String token = JWT.create()
                    .withIssuer("zimbaue")
                    .withSubject(user.getEmail())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex){
            throw new RuntimeException("Erro ao criar token", ex);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret-key");
            return JWT.require(algorithm)
                    .withIssuer("zimbaue")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            return "";
        }
    }
}
