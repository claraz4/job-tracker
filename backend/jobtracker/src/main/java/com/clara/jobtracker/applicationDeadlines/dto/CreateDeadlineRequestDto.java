package com.clara.jobtracker.applicationDeadlines.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateDeadlineRequestDto(
        @NotNull
        Long applicationId,

        @NotBlank
        String title,

        String details,

        @NotNull
        LocalDateTime dueAt
) {
}
