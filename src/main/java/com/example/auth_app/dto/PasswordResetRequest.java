package com.example.auth_app.dto;

public class PasswordResetRequest {

    private String token; // Token de réinitialisation
    private String newPassword; // Nouveau mot de passe

    // Getters et Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}