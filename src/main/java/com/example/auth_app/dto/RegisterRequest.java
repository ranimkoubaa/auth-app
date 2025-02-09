package com.example.auth_app.dto;


public class RegisterRequest {

    private String username; // Nom d'utilisateur
    private String email; // Email de l'utilisateur
    private String password; // Mot de passe brut

    // Getters et Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
