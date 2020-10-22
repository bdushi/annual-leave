package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.authentication.AuthenticationFacade;
import de.dlh.lhind.annualleave.dto.UserDto;
import de.dlh.lhind.annualleave.event.OnRegistrationEvent;
import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

import static de.dlh.lhind.annualleave.common.Common.generateRandomPassword;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final ApplicationEventPublisher applicationEventPublisher;
    private UserServiceImpl(
            UserRepository userRepository,
            AuthenticationFacade authenticationFacade,
            ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         try {
             return userRepository
                    .findByUsername(s)
                    .orElseThrow(
                            (Supplier<Throwable>) () -> new RuntimeException("User not Found")
                    );
        } catch (Throwable throwable) {
            throw new RuntimeException("User not Found");
        }
    }
    @Override
    public User findByUsername(String username) {
        try {
            return userRepository
                    .findByUsername(username)
                    .orElseThrow(
                            (Supplier<Throwable>) () -> new RuntimeException("User not Found")
                    );
        } catch (Throwable throwable) {
            throw new RuntimeException("User not Found");
        }
    }

    @Override
    public User register(UserDto userDto, String appUrl) {
        String password = generateRandomPassword(8, 97, 122);
        User user = new User(
                0,
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUsername(),
                new BCryptPasswordEncoder().encode(password),
                userDto.getEmail(),
                userDto.getAddress(),
                userDto.getPhone(),
                userDto.getAuthorities(),
                ZonedDateTime.now(),
                true);
        applicationEventPublisher.publishEvent(new OnRegistrationEvent(user, appUrl, password));
        return userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto) {
        User user = findByUsername(authenticationFacade.authentication().getName());
        if(user.getUsername().equals(userDto.getUsername())) {
            return userRepository.save(
                    new User(
                            user.getId(),
                            userDto.getFirstName(),
                            userDto.getLastName(),
                            userDto.getUsername(),
                            user.getPassword(),
                            userDto.getEmail(),
                            userDto.getAddress(),
                            userDto.getPhone(),
                            userDto.getAuthorities(),
                            ZonedDateTime.now(),
                            user.isEnable())
            );
        } else {
            throw new RuntimeException("User didn't match");
        }
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updatePassword(String oldPassword, String newPassword) {
        try {
            User user = findByUsername(authenticationFacade.authentication().getName());
            if (new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                return userRepository.save(user);
            } else {
                throw new RuntimeException("Password Must be Matched");
            }
        } catch (UsernameNotFoundException usernameNotFoundException) {
            throw new RuntimeException("User not found", usernameNotFoundException);
        }
    }
}
