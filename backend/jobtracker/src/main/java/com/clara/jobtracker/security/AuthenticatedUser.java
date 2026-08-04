package com.clara.jobtracker.security;

public record AuthenticatedUser(
        Long id,
        String email
) {
}
