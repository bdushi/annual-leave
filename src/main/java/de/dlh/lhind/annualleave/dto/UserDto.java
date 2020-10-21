package de.dlh.lhind.annualleave.dto;

import de.dlh.lhind.annualleave.model.Authority;
import java.time.ZonedDateTime;
import java.util.Collection;

public class UserDto {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final boolean enable;
    private final Collection<Authority> authorities;
    private final ZonedDateTime employmentDate;
    private final String address;
    private final String phone;

    public UserDto(
            String firstName,
            String lastName,
            String username,
            String email,
            boolean enable,
            Collection<Authority> authorities,
            ZonedDateTime employmentDate,
            String address,
            String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.authorities = authorities;
        this.employmentDate = employmentDate;
        this.address = address;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnable() {
        return enable;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public ZonedDateTime getEmploymentDate() {
        return employmentDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
