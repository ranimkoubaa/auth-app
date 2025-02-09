/*
package com.example.auth_app.controller;

import com.example.auth_app.dto.EmailRequest;
import com.example.auth_app.dto.PasswordResetRequest;
import com.example.auth_app.service.PasswordResetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/reset/request")
    public String requestPasswordReset(@RequestBody EmailRequest emailRequest) {
        passwordResetService.sendPasswordResetEmail(emailRequest.getEmail());
        return "Email sent successfully";
    }

    @PostMapping("/reset/confirm")
    public String resetPassword(@RequestBody PasswordResetRequest resetRequest) {
        passwordResetService.resetPassword(resetRequest.getToken(), resetRequest.getPassword());
        return "Password reset successfully";
    }
}

 */
package com.example.auth_app.controller;

import com.example.auth_app.dto.EmailRequest;
import com.example.auth_app.dto.PasswordResetRequest;
import com.example.auth_app.service.PasswordResetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    /**
     * Endpoint pour demander une réinitialisation de mot de passe.
     *
     * @param emailRequest Les données contenant l'email.
     * @return Message de confirmation.
     */
    @PostMapping("/reset/request")
    public String requestPasswordReset(@RequestBody EmailRequest emailRequest) {
        passwordResetService.sendPasswordResetEmail(emailRequest.getEmail());
        return "Email sent successfully";
    }
    @PostMapping("/reset/confirm")
    public String confirmPasswordReset(@RequestBody PasswordResetRequest resetRequest) {
        if (resetRequest.getNewPassword() == null || resetRequest.getNewPassword().isEmpty()) {
            throw new IllegalArgumentException("Le nouveau mot de passe ne peut pas être vide ou null");
        }

        passwordResetService.resetPassword(resetRequest.getToken(), resetRequest.getNewPassword());
        return "Password reset successfully";
    }
}