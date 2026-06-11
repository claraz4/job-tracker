package com.clara.jobtracker.application.dto;

import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusRequestDto(
        @NotNull
        Long applicationId,

        @NotNull
        Status newStatus
) {
}
