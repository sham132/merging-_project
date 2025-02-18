package com.thinkconstructive.postservice;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/generate-token")
    public String generateToken(@RequestParam String privateKey) {
        try {
            return generateJwtToken(privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating JWT token";
        }
    }

    private String generateJwtToken(String privateKeyContent) throws Exception {
        // Decode the provided private key
        byte[] decodedPrivateKeyBytes = java.util.Base64.getDecoder().decode(privateKeyContent);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKeyBytes));

        // Build the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("ops", "auth");
        payload.put("client_uuid", "1192b415-4b81-4301-a836-169b2b8eb16b");
        payload.put("ops", "auth1");
        payload.put("client_uuid2", "1192b415-4b81-4301-a836-169b2b8eb16b");
        payload.put("ops", "auth2");
        payload.put("client_uuid3", "1192b415-4b81-4301-a836-169b2b8eb16b");
        // Generate JWT token
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
    
    private String generateJwtToken1123(String privateKeyContent) throws Exception {
        // Decode the provided private key
        byte[] decodedPrivateKeyBytes = java.util.Base64.getDecoder().decode(privateKeyContent);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decodedPrivateKeyBytes));

        // Build the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("ops", "auth");
        payload.put("client_uuid", "1192b415-4b81-4301-a836-169b2b8eb16b");

        // Generate JWT token
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
}
