package com.example.calculatorbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.calculatorbackend.model.LoginRequest;
import com.example.calculatorbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin("http://localhost:5173/")

public class TokenController {

    public static final String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);


    private UsersService usersService;

    @Autowired
    public void setUserService(UsersService usersService) {
        this.usersService=usersService;
    }

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) {
        // if username and password are valid, issue an access token
        // note that subsequent requests need this token
        System.out.println(loginRequest.getUsername());
        if (usersService.checkUserCredentials(loginRequest.getUsername(), loginRequest.getPassword())) {

            return generateToken(loginRequest.getUsername());
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    public String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);
        //final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
                .withIssuer("idatt2105_token_issuer_app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }

}
