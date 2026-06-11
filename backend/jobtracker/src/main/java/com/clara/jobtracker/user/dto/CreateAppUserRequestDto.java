package com.clara.jobtracker.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAppUserRequestDto(
        @NotBlank(message = "Username is required")
        @Size(message = "Username must not exceed 50 characters", max = 50)
        String username,

        @NotBlank(message = "Name is required")
        @Size(message = "Name must not exceed 150 characters", max = 150)
        String name,

        @NotBlank(message = "Position is required")
        @Size(message = "Position must not exceed 150 characters", max = 150)
        String position
) {}
