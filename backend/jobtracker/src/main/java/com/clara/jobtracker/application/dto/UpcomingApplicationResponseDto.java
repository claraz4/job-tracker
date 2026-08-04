package com.clara.jobtracker.application.dto;

import com.clara.jobtracker.application.enums.Priority;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;

import java.time.LocalDate;

public record UpcomingApplicationResponseDto(
        Long id,
        String position,
        String company,
        Priority priority,
        Status currentStatus,
        LocalDate date
) {
}
