package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.*;
import com.clara.jobtracker.application.enums.ApplicationSort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResponseDto> getAllApplications(@PathVariable Long userId, @RequestParam(required = false, defaultValue = "CREATED") ApplicationSort sort) {
        return applicationService.getAllApplications(userId, sort);
    }

    @GetMapping("/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponseDto getApplicationById(@PathVariable Long applicationId, @PathVariable Long userId) {
        return applicationService.getApplicationById(applicationId, userId);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsStatsResponseDto getApplicationsStats(@PathVariable Long userId) {
        return applicationService.getApplicationsStats(userId);
    }

    @GetMapping("/upcoming")
    @ResponseStatus(HttpStatus.OK)
    public List<UpcomingApplicationResponseDto> getUpcomingApplications(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "false") Boolean deadlinesOnly,
            @RequestParam(required = false) LocalDate date) {
        return applicationService.getUpcomingApplications(userId, deadlinesOnly, date);
    }

    @PostMapping("/{applicationId}/status-history")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto updateApplicationStatus(@PathVariable Long userId, @Valid @RequestBody UpdateApplicationStatusRequestDto request) {
        return applicationService.updateApplicationStatus(userId, request);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto createApplication(@PathVariable Long userId, @Valid @RequestBody CreateApplicationRequestDto request) {
        return applicationService.createApplication(userId, request);
    }
}
