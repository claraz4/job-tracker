package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.ApplicationResponseDto;
import com.clara.jobtracker.application.dto.CreateApplicationRequestDto;
import com.clara.jobtracker.application.dto.UpdateApplicationStatusRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<ApplicationResponseDto> getAllApplications(@PathVariable Long userId) {
        return applicationService.getAllApplications(userId);
    }

    @GetMapping("/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponseDto getApplicationById(@PathVariable Long applicationId, @PathVariable Long userId) {
        return applicationService.getApplicationById(applicationId, userId);
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
