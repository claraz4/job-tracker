package com.clara.jobtracker.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank
        String username,

        @NotBlank
        String name,

        String position,

        @NotBlank
        @Size(min = 8, max = 255)
        String password
) {
}
