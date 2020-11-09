package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User updatePassword(String oldPassword , String newPassword);
    User resetPassword(String username, String appUrl);
    User findByUsername(String username);
    User register(UserDto userDto , String appUrl);
    User update(UserDto userDto);
    Iterable<User> findAll();
    Iterable<User> findAllByAuthority();
}
