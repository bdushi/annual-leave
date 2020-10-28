package de.dlh.lhind.annualleave.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.function.Function;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Credentials credentials = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException("Invalid Username Or Password", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
          String[] auth = authResult
                .getAuthorities()
                .stream()
                .map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).toArray(String[]::new);
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withArrayClaim(jwtConfig.getAuthorities(), auth)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC512(jwtConfig.getSecret().getBytes()));
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getTokenPrefix() + token);
    }
}
