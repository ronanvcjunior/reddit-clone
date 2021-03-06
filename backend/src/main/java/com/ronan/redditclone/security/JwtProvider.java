package com.ronan.redditclone.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.ronan.redditclone.exception.SpringRedditException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());

        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringRedditException("Exception occureed while loading keystore");
        }
    }
    
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        return Jwts.builder()
        .setSubject(principal.getUsername())
        .signWith(getPrivateKey())
        .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
        .compact();
    }

    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis))).compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());

        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Excepiton occured while retrieving private key from keystore");
        }
    }

    public boolean validateToken(String jwt) {
        // Jws<Claims> jws = parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        var parser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
        parser.parseClaimsJws(jwt);

        return true;
    }

    public PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();

        } catch (KeyStoreException e) {
            throw new SpringRedditException("Excepiton occured while retrieving public key from keystore");
        }
    }

    public String getUsernameFromJwt(String token) {
        // Claims claims = parser().
        //         setSigningKey(getPublicKey())
        //         .parseClaimsJws(token)
        //         .getBody();

        // return claims.getSubject();

        var parser = Jwts.parserBuilder().setSigningKey(getPublicKey()).build();
        var claims = parser.parseClaimsJws(token);

        return claims.getBody().getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return this.jwtExpirationInMillis;
    }
}
