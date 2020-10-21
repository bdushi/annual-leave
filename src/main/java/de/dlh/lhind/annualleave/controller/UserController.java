package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<User> findByUsername(@AuthenticationPrincipal @RequestParam("username")String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    ResponseEntity<User> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return new ResponseEntity<>(userService.updatePassword(oldPassword, newPassword), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<User> create(@RequestBody UserDto userDto , HttpServletRequest request) {
        return new ResponseEntity<>(
                userService
                        .register(
                                userDto,
                                "http://${request.serverName}:${request.serverPort}${request.contextPath}"
                        ),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    ResponseEntity<User> update(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
    }
}
