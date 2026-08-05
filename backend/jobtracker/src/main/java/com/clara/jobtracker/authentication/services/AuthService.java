package com.clara.jobtracker.authentication.services;

import com.clara.jobtracker.authentication.dto.LoginRequestDto;
import com.clara.jobtracker.authentication.dto.LoginResponseDto;
import com.clara.jobtracker.authentication.dto.RefreshTokenRequestDto;
import com.clara.jobtracker.authentication.dto.RegisterRequestDto;
import com.clara.jobtracker.authentication.models.RefreshToken;
import com.clara.jobtracker.common.exceptions.DuplicateResourceException;
import com.clara.jobtracker.security.JwtService;
import com.clara.jobtracker.user.AppUser;
import com.clara.jobtracker.user.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, AppUserRepository userRepository, JwtService jwtService, RefreshTokenService refreshTokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        AppUser user = userRepository.findByUsername(request.username()).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new LoginResponseDto(accessToken, refreshToken.getToken());
    }

    @Transactional
    public void register(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateResourceException("This username already exists.");
        }

        AppUser user = new AppUser(request.username(), request.name(), request.position(), passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    public LoginResponseDto refreshToken(RefreshTokenRequestDto request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.refreshToken());
        AppUser user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(user.getId(), user.getUsername());
        return new LoginResponseDto(newAccessToken, refreshToken.getToken());
    }

    public void logout(Long userId) {
        refreshTokenService.logout(userId);
    }
}
