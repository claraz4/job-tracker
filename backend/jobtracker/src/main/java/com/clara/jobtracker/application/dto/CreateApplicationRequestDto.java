package com.clara.jobtracker.application.dto;

import com.clara.jobtracker.application.enums.JobType;
import com.clara.jobtracker.application.enums.Priority;
import com.clara.jobtracker.application.enums.WorkMode;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateApplicationRequestDto(
        @NotBlank
        String position,

        @NotBlank
        String company,

        @NotBlank
        String location,

        @NotNull
        JobType jobType,

        @NotNull
        Priority priority,

        @NotNull
        Status currentStatus,

        LocalDate dateApplied,

        String notes,

        String requirements,

        WorkMode workMode
) {
}
