package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User AS u WHERE lower(u.username) = lower(:username) AND u.enable = true")
    Optional<User> findByUsername(String username);
}
