package com.clara.jobtracker.applicationDeadlines.dto;

import java.time.LocalDateTime;

public record DeadlineDetailsResponseDto(
        Long id,
        Long applicationId,
        LocalDateTime createdAt,
        String title,
        String details,
        LocalDateTime dueAt,
        boolean completed,

        String position,
        String company
) {
}
