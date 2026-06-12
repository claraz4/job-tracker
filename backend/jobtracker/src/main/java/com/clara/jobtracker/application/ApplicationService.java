package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.ApplicationResponseDto;
import com.clara.jobtracker.application.dto.CreateApplicationRequestDto;
import com.clara.jobtracker.application.dto.UpdateApplicationStatusRequestDto;
import com.clara.jobtracker.applicationStatusHistory.ApplicationStatusHistory;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import com.clara.jobtracker.user.AppUser;
import com.clara.jobtracker.user.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final AppUserRepository appUserRepository;

    public ApplicationService(ApplicationRepository applicationRepository, AppUserRepository appUserRepository) {
        this.applicationRepository = applicationRepository;
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public ApplicationResponseDto createApplication(Long userId, CreateApplicationRequestDto request) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        LocalDate dateApplied = request.dateApplied();

        if (request.dateApplied() == null) {
            dateApplied = LocalDate.now();
        }

        ApplicationStatusHistory history = new ApplicationStatusHistory(dateApplied, request.currentStatus());

        Application newApplication = new Application(
                request.position(),
                request.company(),
                request.location(),
                request.jobType(),
                request.priority(),
                request.currentStatus(),
                dateApplied,
                request.notes(),
                request.requirements(),
                request.workMode()
        );

        user.addApplication(newApplication);
        newApplication.addStatusHistory(history);

        Application savedApplication = applicationRepository.save(newApplication);
        return toResponse(savedApplication);
    }

    public List<ApplicationResponseDto> getAllApplications(Long userId) {
        appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return applicationRepository.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    public ApplicationResponseDto getApplicationById(Long applicationId, Long userId) {
        Application application = applicationRepository.findByIdAndUserId(applicationId, userId).orElseThrow(() -> new ResourceNotFoundException(
                "Application not found with id " + applicationId + " for user " + userId
        ));

        return toResponse(application);
    }

    @Transactional
    public ApplicationResponseDto updateApplicationStatus(Long userId, UpdateApplicationStatusRequestDto request) {
        Application application = applicationRepository.findById(request.applicationId()).orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        application.setCurrentStatus(request.newStatus());

        ApplicationStatusHistory newHistory = new ApplicationStatusHistory(LocalDate.now(), request.newStatus());
        application.addStatusHistory(newHistory);

        Application savedApplication = applicationRepository.save(application);
        return toResponse(savedApplication);
    }

    private ApplicationResponseDto toResponse(Application application) {
        return new ApplicationResponseDto(
                application.getId(),
                application.getPosition(),
                application.getCompany(),
                application.getLocation(),
                application.getJobType(),
                application.getPriority(),
                application.getCurrentStatus(),
                application.getDateApplied(),
                application.getNotes(),
                application.getRequirements(),
                application.getWorkMode()
        );
    }
}
