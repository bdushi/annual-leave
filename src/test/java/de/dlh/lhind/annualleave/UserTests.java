package de.dlh.lhind.annualleave;

import de.dlh.lhind.annualleave.common.Roles;
import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.model.Authority;
import de.dlh.lhind.annualleave.service.AuthorityService;
import de.dlh.lhind.annualleave.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTests {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @Test
    void contextLoads() throws Throwable {
        Authority authorities = authorityService.authority(Roles.EMPLOYEE).orElseThrow((Supplier<Throwable>) () -> new Exception("Authority Not Founded"));
        userService.register(
                new UserDto(
                        "John",
                        "Doo",
                        "johndoo",
                        "johndoo@gmail.com",
                        true,
                        authorities.getRoles(),
                        ZonedDateTime.now(),
                        "Rruga Medar Shtylla",
                        "0699897887"),
                "http://localhost:8080"
        );
    }

    @Test
    void resetPassword() {
        userService.resetPassword("http://localhost:8080");
    }
}
