package com.clara.jobtracker.authentication;

import com.clara.jobtracker.authentication.models.RefreshToken;
import com.clara.jobtracker.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(AppUser user);
    void deleteByUserId(Long userId);
}
