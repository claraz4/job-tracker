package com.clara.jobtracker.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RefreshTokenCookieFactory {

    @Value("${security.jwt.refresh-token-expiration-days}")
    private long refreshTokenExpirationDays;

    @Value("${security.cookie.secure}")
    private boolean securiyCookieSecure;

    public ResponseCookie create(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(securiyCookieSecure)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ofDays(refreshTokenExpirationDays))
                .build();
    }

    public ResponseCookie clear() {
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(securiyCookieSecure)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ZERO)
                .build();
    }
}
