package de.dlh.lhind.annualleave;

import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.model.Authority;
import de.dlh.lhind.annualleave.service.AuthorityService;
import de.dlh.lhind.annualleave.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.List;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserService userService;
    private AuthorityService authorityService;

    @Test
    private void createUser() throws Throwable {
        List<Authority> authorities = authorityService.findByAuthority("EMPLOYEE");
        userService.register(
                new UserDto(
                     "John",
                     "Doo",
                     "jhondoo",
                        "johndoo@gmail.com",
                        true,
                        authorities,
                        ZonedDateTime.now(),
                        "Rruga Medar Shtylla",
                        "0699897887"),
                "http://localhost:8080"
        );
    }

    @Test
    private void resetPassword() {
        userService.resetPassword("brunodushi", "http://localhost:8080");
    }
}
