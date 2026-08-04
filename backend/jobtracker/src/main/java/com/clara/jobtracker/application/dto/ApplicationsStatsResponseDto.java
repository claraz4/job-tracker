package com.clara.jobtracker.application.dto;

public record ApplicationsStatsResponseDto(
        Long active,
        Long applied,
        Long interviews,
        Long offers
) {
}
