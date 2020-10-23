package de.dlh.lhind.annualleave.security;

public class SecurityConstants {
    static final String SECRET = "SecretKeyToGenJWTs";
    static final long EXPIRATION_TIME = 864000000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String AUTHORITIES = "AUTHORITIES";
    static final String SIGN_IN_URL = "/login";
    static final String RESET_PASSWORD = "/user/resetPassword";
    static final String CREATE_URL = "/user/create";
    static final String H2_CONSOLE = "/h2-console/**";
}
