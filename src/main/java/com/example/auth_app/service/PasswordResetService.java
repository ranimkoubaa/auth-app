/*package com.example.auth_app.service;

import com.example.auth_app.model.User;
import com.example.auth_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordResetService(UserRepository userRepository, JavaMailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendPasswordResetEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString(); // Générer un token unique
        user.setResetToken(token); // Stocker le token dans l'utilisateur
        userRepository.save(user); // Sauvegarder l'utilisateur mis à jour

        sendEmail(user.getEmail(), token); // Envoyer l'email avec le token
    }

    private void sendEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Click the link below to reset your password: http://localhost:4200/reset-password?token=" + token);
        mailSender.send(message);
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null); // Réinitialise le token après la mise à jour du mot de passe
        userRepository.save(user);
    }
}

 */
/*
package com.example.auth_app.service;

import com.example.auth_app.model.User;
import com.example.auth_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public PasswordResetService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString(); // Générer un token unique
        user.setResetToken(token); // Stocker le token dans l'utilisateur
        userRepository.save(user); // Sauvegarder l'utilisateur mis à jour

        sendEmail(user.getEmail(), token); // Envoyer l'email avec le token
    }

    private void sendEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Click the link below to reset your password: http://localhost:4200/reset-password?token=" + token);

        try {
            mailSender.send(message); // Tentative d'envoi de l'email
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e); // Gérer les erreurs d'envoi
        }
    }

}

 */
package com.example.auth_app.service;

import com.example.auth_app.model.User;
import com.example.auth_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Autowired
    public PasswordResetService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender; // Initialiser mailSender ici
    }

    /**
     * Envoie un email de réinitialisation de mot de passe.
     *
     * @param email L'email de l'utilisateur.
     */
    /*
    public void sendPasswordResetEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString(); // Générer un token unique
        user.setResetToken(token); // Stocker le token dans l'utilisateur
        userRepository.save(user); // Sauvegarder l'utilisateur mis à jour

        sendEmail(user.getEmail(), token); // Envoyer l'email avec le token
    }
    */
    public void sendPasswordResetEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString(); // Générer un token unique

        // Définir une date d'expiration pour le token (par exemple, 1 heure)
        Timestamp expirationTime = new Timestamp(System.currentTimeMillis() + 3600000); // 3600000 = 1 heure en millisecondes

        user.setResetToken(token);
        user.setResetTokenExpiresAt(expirationTime); // Définir la date d'expiration

        // Sauvegarder l'utilisateur mis à jour
        userRepository.save(user);

        // Envoyer l'email avec le token
        sendEmail(user.getEmail(), token);
    }
    private void sendEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Click the link below to reset your password: http://localhost:4200/reset-password?token=" + token);

        try {
            mailSender.send(message); // Utiliser mailSender ici
            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    /**
     * Réinitialise le mot de passe d'un utilisateur.
     *
     * @param token         Le token de réinitialisation.
     * @param newPassword   Le nouveau mot de passe brut.
     */
    public void resetPassword(String token, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Le nouveau mot de passe ne peut pas être vide ou null");
        }

        Optional<User> userOptional = userRepository.findByResetToken(token);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }

        User user = userOptional.get();

        // Vérification supplémentaire : Expiration du token
        if (isTokenExpired(user.getResetTokenExpiresAt())) { // Ajoutez une méthode pour vérifier l'expiration
            throw new RuntimeException("Token expired");
        }

        // Hacher le nouveau mot de passe
        user.setPassword(passwordEncoder.encode(newPassword));

        // Réinitialiser le token après mise à jour
        user.setResetToken(null);
        user.setResetTokenExpiresAt(null);

        // Sauvegarder les modifications dans la base de données
        userRepository.save(user);
    }

    private boolean isTokenExpired(java.sql.Timestamp expirationDate) {
        return expirationDate != null && expirationDate.before(new java.sql.Timestamp(System.currentTimeMillis()));
    }

    /**
     * Teste l'envoi d'email.
     *
     * @param to Adresse email du destinataire.
     */
    public void testSendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Test Email");
        message.setText("Ceci est un email de test.");

        try {
            mailSender.send(message); // Utiliser mailSender ici
            System.out.println("Email sent successfully to: " + to);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

}