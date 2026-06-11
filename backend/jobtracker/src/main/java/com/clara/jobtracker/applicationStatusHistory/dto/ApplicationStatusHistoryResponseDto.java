package com.clara.jobtracker.applicationStatusHistory.dto;

import com.clara.jobtracker.applicationStatusHistory.enums.Status;

import java.time.LocalDate;

public record ApplicationStatusHistoryResponseDto(
        Long id,
        LocalDate date,
        Status status
) {
}
