package com.example.auth_app;

import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        // Générer une clé de 512 bits (secure enough for HS512)
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // Encoder la clé en Base64
        String encodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Votre clé secrète JWT encodée : " + encodedKey);
    }
}
