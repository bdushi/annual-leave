package de.dlh.lhind.annualleave.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * https://vladmihalcea.com/mariadb-10-3-database-sequences/
 */

@Entity
@Table
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false, unique = true, nullable = false)
    private final long id;
    private final String firstName;
    private final String lastName;
    @Column(unique = true, nullable = false, updatable = false)
    private final String username;
    @Column(unique = true, nullable = false, updatable = false)
    private final String email;
    @Column(nullable = false)
    private String password;
    private final boolean enable;
    @ManyToMany(fetch = FetchType.EAGER)
    private final Collection<Authority> authorities;
    private final ZonedDateTime employmentDate;
    private final String address;
    private final String phone;

    /**
     * Default Constructor
     */
    public User() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.username = "";
        this.email = "";
        this.enable = false;
        this.authorities = new ArrayList<>();
        this.employmentDate = ZonedDateTime.now();
        this.address = "";
        this.phone = "";
    }

    public User(
            long id,
            String firstName,
            String lastName,
            String username,
            String password,
            String email,
            String address,
            String phone,
            Collection<Authority> authorities,
            ZonedDateTime employmentDate,
            boolean enable) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.authorities = authorities;
        this.employmentDate = employmentDate;
        this.enable = enable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
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
