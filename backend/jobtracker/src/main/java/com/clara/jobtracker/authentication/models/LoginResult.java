package com.clara.jobtracker.authentication.models;

public record LoginResult(
        String accessToken,
        String refreshToken
) {
}
