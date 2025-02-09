package com.example.auth_app.repository;

import com.example.auth_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String token);
    boolean existsByUsername(String username); // Vérifier si le username existe
    boolean existsByEmail(String email); // Vérifier si l'email existe
}
