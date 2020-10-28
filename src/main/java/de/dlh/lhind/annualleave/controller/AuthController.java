package de.dlh.lhind.annualleave.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * https://ducmanhphan.github.io/2019-02-22-Logout-in-Spring-Boot/
 *
 * https://stackoverflow.com/questions/43569723/jwt-authentication-how-to-implement-logout
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/logout")
    public String fetchSignOutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.setClearAuthentication(true);
            securityContextLogoutHandler.setInvalidateHttpSession(true);
            securityContextLogoutHandler
                    .logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
