package com.example.calculatorbackend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.calculatorbackend.controller.TokenController;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LogManager.getLogger(JWTAuthorizationFilter.class);

    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_" + USER;



    @Override
    protected void doFilterInternal(

            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);

            return;
        }


        String token = header.substring(7);
        final String username = validateTokenAndGetUserId(token);
        if (username == null) {
            // validation failed or token expired
            filterChain.doFilter(request, response);
            return;
        }


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER)));
        SecurityContextHolder.getContext().setAuthentication(auth);


        filterChain.doFilter(request, response);
    }

    public String validateTokenAndGetUserId(final String token) {
        try {
            final Algorithm hmac512 = Algorithm.HMAC512(TokenController.keyStr);
            final JWTVerifier verifier = JWT.require(hmac512).build();
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            LOGGER.warn("token is invalid: {}", verificationEx.getMessage());
            return null;
        }
    }

}
