package com.clara.jobtracker.user.dto;

public record AppUserResponseDto(
        Long id,
        String username,
        String name,
        String position
) {}
