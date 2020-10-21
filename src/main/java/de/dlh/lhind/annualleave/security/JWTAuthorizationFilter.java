package de.dlh.lhind.annualleave.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static de.dlh.lhind.annualleave.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // Read the Authorization header, where the JWT token should be
        String header = httpServletRequest.getHeader(HEADER_STRING);
        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        try {
            // Parse the token.
            DecodedJWT decodedUser = JWT
                    .require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(header.replace(TOKEN_PREFIX, ""));
            // If header is present, try grab user principal from database and perform authorization
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    decodedUser.getSubject(),
                                    null,
                                    Arrays.stream(decodedUser
                                            .getClaim(AUTHORITIES)
                                            .asArray(String.class))
                                            .map(new Function<String, GrantedAuthority>() {
                                                @Override
                                                public GrantedAuthority apply(String s) {
                                                    return new GrantedAuthority() {
                                                        @Override
                                                        public String getAuthority() {
                                                            return s;
                                                        }
                                                    };
                                                }
                                            })
                                    .collect(Collectors.toList())
                            )
                    );
        } catch (Exception e) {
            throw new RuntimeException("Invalid JsonWebToken", e);
        }
        // Continue filter execution
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
