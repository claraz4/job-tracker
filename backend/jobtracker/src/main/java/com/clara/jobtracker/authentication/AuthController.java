package com.clara.jobtracker.authentication;

import com.clara.jobtracker.authentication.dto.*;
import com.clara.jobtracker.authentication.services.AuthService;
import com.clara.jobtracker.security.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequestDto request) {
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto refresh(@Valid @RequestBody RefreshTokenRequestDto request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@AuthenticationPrincipal AuthenticatedUser user) {
        authService.logout(user.id());
    }
}
