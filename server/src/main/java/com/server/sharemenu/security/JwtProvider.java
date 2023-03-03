package com.server.sharemenu.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.sharemenu.common.User;
import com.server.sharemenu.exception.TokenTypeException;
import com.server.sharemenu.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    public static final String AUTH_BEARER = "Bearer ";

    @Value("${sharemenu.app.jwtSecret}")
    private String jwtSecret;

    @Value("${sharemenu.app.jwtExpirationMs}")
    private long jwtAccessTokenExpiration;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public JwtProvider() {
    }

    public String generateHash() {
        return UUID.randomUUID().toString();
    }

    private DecodedJWT getTokenByAuthorization(String authorizationHeader) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        String token = authorizationHeader.substring(AUTH_BEARER.length());
        return verifier.verify(token);
    }

    public void validateToken(HttpServletResponse response, HttpServletRequest request, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getHeader(AUTHORIZATION) != null && request.getHeader(AUTHORIZATION).startsWith(AUTH_BEARER)) {
            try {
                DecodedJWT decodedJWT = getTokenByAuthorization(request.getHeader(AUTHORIZATION));
                String email = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                stream(roles).forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role));
                });

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
                        null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(request, response);

            } catch (Exception exception) {
                logger.error("Error logging in: {}", exception);
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public Map<String, String> generateTokens(User user) {
        Map<String, String> tokens = new HashMap<>();
        String access_token;

        access_token = generateAccessToken(user);

        tokens.put("access_token", access_token);

        return tokens;
    }

    private String generateToken(User user, TokenType tokenType) throws TokenTypeException {

        if (user.getAuthorities() == null) {
            user.setAuthorities(userDetailsService.packAuthorities(user));
        }

        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        Date dateExpires;

        switch (tokenType) {
            case ACCESS_TOKEN:
                dateExpires = new Date(System.currentTimeMillis() + jwtAccessTokenExpiration);
                break;
            default:
                throw new TokenTypeException("Does not exist kind of token");
        }

        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(dateExpires)
                .withClaim("roles",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateAccessToken(User user) {
        try {
            return generateToken(user, TokenType.ACCESS_TOKEN);
        } catch (TokenTypeException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
