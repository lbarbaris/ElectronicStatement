package com.lbarbaris.springboot.electronicstatement.securityRepository;

import com.lbarbaris.springboot.electronicstatement.securityEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);
}
