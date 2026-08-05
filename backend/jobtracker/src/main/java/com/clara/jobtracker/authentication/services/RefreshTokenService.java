package com.clara.jobtracker.authentication.services;

import com.clara.jobtracker.authentication.RefreshTokenRepository;
import com.clara.jobtracker.authentication.models.RefreshToken;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import com.clara.jobtracker.user.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional
public class RefreshTokenService {

    private final long refreshTokenDurationDays;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(
            @Value("${security.jwt.refresh-token-expiration-days}")
            long refreshTokenDurationDays,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.refreshTokenDurationDays = refreshTokenDurationDays;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(AppUser user) {

        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElseGet(RefreshToken::new);

        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plus(refreshTokenDurationDays, ChronoUnit.DAYS));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    public void logout(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}
