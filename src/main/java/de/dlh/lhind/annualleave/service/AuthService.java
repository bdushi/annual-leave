package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthService(JwtHelper jwtHelper, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtHelper = jwtHelper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String signIn(String username, String password) {
        User user = userService.findByUsername(username);
		if (passwordEncoder.matches(password, user.getPassword())) {
			return jwtHelper.createJwtForClaims(user);
		} else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }
}
