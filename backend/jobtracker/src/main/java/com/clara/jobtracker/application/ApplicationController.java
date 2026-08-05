package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.*;
import com.clara.jobtracker.application.enums.ApplicationSort;
import com.clara.jobtracker.security.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationResponseDto> getAllApplications(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam(required = false, defaultValue = "CREATED") ApplicationSort sort
    ) {
        return applicationService.getAllApplications(user.id(), sort);
    }

    @GetMapping("/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponseDto getApplicationById(
            @PathVariable Long applicationId,
            @AuthenticationPrincipal AuthenticatedUser user
    ) {
        return applicationService.getApplicationById(applicationId, user.id());
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationsStatsResponseDto getApplicationsStats(@AuthenticationPrincipal AuthenticatedUser user) {
        return applicationService.getApplicationsStats(user.id());
    }

    @PostMapping("/{applicationId}/status-history")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto updateApplicationStatus(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody UpdateApplicationStatusRequestDto request
    ) {
        return applicationService.updateApplicationStatus(user.id(), request);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto createApplication(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody CreateApplicationRequestDto request
    ) {
        return applicationService.createApplication(user.id(), request);
    }
}
