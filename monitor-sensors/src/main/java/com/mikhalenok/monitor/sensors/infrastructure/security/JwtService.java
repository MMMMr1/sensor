package com.mikhalenok.monitor.sensors.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry-time-in-seconds}")
    private Long expirationTime;


    public String generateJwt(Authentication authentication) {
        String username = authentication.getName();
        return Jwts.
                builder()
                .issuer(issuer)
                .subject(username)
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(expirationTime, ChronoUnit.SECONDS)))
                .claim("username", username)
                .claim("authorities", authentication.getAuthorities())
                .signWith(getSecretKey()).compact();
    }

    public Authentication setAuthentication(String token) {
        Claims payload = parseClaimsFromToken(token);
        String username = payload.getSubject();
        List<SimpleGrantedAuthority> authorities = getSimpleGrantedAuthorities(payload);
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

    private List<SimpleGrantedAuthority> getSimpleGrantedAuthorities(Claims payload) {
        List<?> authoritiesRaw = payload.get("authorities", List.class);
        return authoritiesRaw.stream()
                .map(auth -> new SimpleGrantedAuthority((String) ((LinkedHashMap<?, ?>) auth).get("authority")))
                .toList();
    }

    public boolean validateToken(String token) {
        return Optional.ofNullable(token)
                .map(this::parseClaimsFromToken)
                .map(claims -> !claims.getExpiration().before(new Date(System.currentTimeMillis())))
                .orElse(false);
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Claims parseClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build().parseSignedClaims(token).getPayload();
    }
}