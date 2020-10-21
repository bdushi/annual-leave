package de.dlh.lhind.annualleave.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication authentication();
}
