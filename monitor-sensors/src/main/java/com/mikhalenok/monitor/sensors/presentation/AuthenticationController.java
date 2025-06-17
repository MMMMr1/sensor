package com.mikhalenok.monitor.sensors.presentation;

import com.mikhalenok.monitor.sensors.presentation.model.auth.RegistrationRq;
import com.mikhalenok.monitor.sensors.presentation.model.auth.LoginRq;
import com.mikhalenok.monitor.sensors.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "User registration and login")
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "User registration", description = "Registers a new user with the Viewer role")
    public void registerUser(@RequestBody RegistrationRq registrationRq) {
        userService.registerUser(registrationRq);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
    public String login(@RequestBody LoginRq request) {
        return userService.loginAndGetToken(request.username(), request.password());
    }
}
