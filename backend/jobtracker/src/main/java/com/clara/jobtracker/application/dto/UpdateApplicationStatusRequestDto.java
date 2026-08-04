package com.clara.jobtracker.application.dto;

import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateApplicationStatusRequestDto(
        @NotNull
        Long applicationId,

        @NotNull
        Status newStatus,

        @NotNull
        LocalDate date
) {
}
