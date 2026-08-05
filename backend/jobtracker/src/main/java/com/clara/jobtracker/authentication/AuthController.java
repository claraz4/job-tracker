package com.clara.jobtracker.authentication;

import com.clara.jobtracker.authentication.dto.*;
import com.clara.jobtracker.authentication.models.LoginResult;
import com.clara.jobtracker.authentication.services.AuthService;
import com.clara.jobtracker.security.AuthenticatedUser;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenCookieFactory refreshTokenCookieFactory;

    public AuthController(AuthService authService, RefreshTokenCookieFactory refreshTokenCookieFactory) {
        this.authService = authService;
        this.refreshTokenCookieFactory = refreshTokenCookieFactory;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequestDto request) {
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request, HttpServletResponse response) {
        LoginResult result = authService.login(request);

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                refreshTokenCookieFactory.create(result.refreshToken()).toString()
        );

        return ResponseEntity.ok(new LoginResponseDto(result.accessToken()));
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> refresh(
            @CookieValue("refreshToken") String refreshToken,
            HttpServletResponse response
    ) {
        LoginResult result = authService.refreshToken(refreshToken);
        response.addHeader(
                HttpHeaders.SET_COOKIE,
                refreshTokenCookieFactory.create(result.refreshToken()).toString()
        );

        return ResponseEntity.ok(new LoginResponseDto(result.accessToken()));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(
            @AuthenticationPrincipal AuthenticatedUser user,
            HttpServletResponse response
    ) {
        authService.logout(user.id());

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                refreshTokenCookieFactory.clear().toString()
        );
    }
}
