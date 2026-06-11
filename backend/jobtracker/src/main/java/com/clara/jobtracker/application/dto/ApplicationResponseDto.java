package com.clara.jobtracker.application.dto;

import com.clara.jobtracker.application.enums.JobType;
import com.clara.jobtracker.application.enums.Priority;
import com.clara.jobtracker.application.enums.WorkMode;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;

import java.time.LocalDate;

public record ApplicationResponseDto(
        Long id,
        String position,
        String company,
        String location,
        JobType jobType,
        Priority priority,
        Status currentStatus,
        LocalDate dateApplied,
        String notes,
        String requirements,
        WorkMode workMode
) {
}
