package de.dlh.lhind.annualleave.controller;

import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<User> findByUsername() {
        return new ResponseEntity<>(userService.findByUsername(), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/byAuthority")
    ResponseEntity<Iterable<User>> findAllByAuthority() {
        return new ResponseEntity<>(userService.findAllByAuthority(), HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    ResponseEntity<User> updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        return new ResponseEntity<>(userService.updatePassword(oldPassword, newPassword), HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<User> resetPassword(@RequestParam("username") String username, HttpServletRequest request) {
        return new ResponseEntity<>(
                userService
                        .resetPassword(
                                String.format("http://%s:%s%s", request.getServerName(), request.getServerPort(), request.getContextPath())
                        ),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<User> create(@RequestBody UserDto userDto, HttpServletRequest request) {
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
