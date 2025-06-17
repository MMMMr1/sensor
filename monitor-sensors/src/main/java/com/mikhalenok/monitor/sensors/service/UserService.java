package com.mikhalenok.monitor.sensors.service;

import com.mikhalenok.monitor.sensors.data.Authority;
import com.mikhalenok.monitor.sensors.data.User;
import com.mikhalenok.monitor.sensors.data.repository.AuthorityRepository;
import com.mikhalenok.monitor.sensors.data.repository.UserRepository;
import com.mikhalenok.monitor.sensors.infrastructure.exception.LoginException;
import com.mikhalenok.monitor.sensors.infrastructure.exception.UserAlreadyExistsException;
import com.mikhalenok.monitor.sensors.infrastructure.security.JwtService;
import com.mikhalenok.monitor.sensors.presentation.model.auth.RegistrationRq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private static final String AUTHORITY_USER = "VIEWER";

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public void registerUser(RegistrationRq registrationRq) {

        if (userRepository.existsByUsername(registrationRq.username())) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setPassword(registrationRq.password());
        user.setUsername(registrationRq.username());
        Set<Authority> authorities = new HashSet<>(Arrays.asList(getAuthority(AUTHORITY_USER)));
        user.setAuthorities(authorities);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public String loginAndGetToken(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return jwtService.generateJwt(authentication);
        } catch (AuthenticationException e) {
            log.error("Unsuccessful authentication: {}", e.getMessage());
            throw new LoginException("Invalid username or password");
        }
    }

    private Authority getAuthority(String authority) {
        return authorityRepository.findByAuthority(authority)
                .orElseGet(() -> {
                    Authority auth = new Authority();
                    auth.setAuthority(authority);
                    return authorityRepository.save(auth);
                });
    }
}
