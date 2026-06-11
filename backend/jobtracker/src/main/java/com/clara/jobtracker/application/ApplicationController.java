package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.ApplicationResponseDto;
import com.clara.jobtracker.application.dto.CreateApplicationRequestDto;
import com.clara.jobtracker.application.dto.UpdateApplicationStatusRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public List<ApplicationResponseDto> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationResponseDto getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping("/{id}/status-history")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto updateApplicationStatus(@Valid @RequestBody UpdateApplicationStatusRequestDto request) {
        return applicationService.updateApplicationStatus(request);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponseDto createApplication(@Valid @RequestBody CreateApplicationRequestDto request) {
        return applicationService.createApplication(request);
    }
}
