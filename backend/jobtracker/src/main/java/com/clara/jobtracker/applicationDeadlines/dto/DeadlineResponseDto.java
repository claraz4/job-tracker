package com.clara.jobtracker.applicationDeadlines.dto;

import java.time.LocalDateTime;

public record DeadlineResponseDto(
        Long id,
        Long applicationId,
        LocalDateTime createdAt,
        String title,
        String details,
        LocalDateTime dueAt,
        boolean completed
) {
}
