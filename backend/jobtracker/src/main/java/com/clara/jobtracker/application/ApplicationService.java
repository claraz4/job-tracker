package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.*;
import com.clara.jobtracker.application.enums.ApplicationSort;
import com.clara.jobtracker.applicationStatusHistory.ApplicationStatusHistory;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;
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

        LocalDate date = request.dateApplied();

        if (request.dateApplied() == null) {
            date = LocalDate.now();
        }

        ApplicationStatusHistory history = new ApplicationStatusHistory(date, request.currentStatus());

        Application newApplication = new Application(
                request.position(),
                request.company(),
                request.location(),
                request.jobType(),
                request.priority(),
                request.currentStatus(),
                date,
                LocalDate.now(),
                request.notes(),
                request.requirements(),
                request.workMode()
        );

        user.addApplication(newApplication);
        newApplication.addStatusHistory(history);

        Application savedApplication = applicationRepository.save(newApplication);
        return toResponse(savedApplication);
    }

    public List<ApplicationResponseDto> getAllApplications(Long userId, ApplicationSort sort) {
        appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Application> applications = switch (sort) {
            case CREATED -> applicationRepository.findByUserIdOrderByDateAppliedDesc(userId);
            case RECENT_ACTIVITY -> applicationRepository.findByUserIdOrderByLastActivityAtDesc(userId);
        };

        return applications.stream().map(this::toResponse).toList();
    }

    public ApplicationResponseDto getApplicationById(Long applicationId, Long userId) {
        Application application = applicationRepository.findByUserIdAndId(userId, applicationId).orElseThrow(() -> new ResourceNotFoundException(
                "Application not found with id " + applicationId + " for user " + userId
        ));

        return toResponse(application);
    }

    public ApplicationsStatsResponseDto getApplicationsStats(Long userId) {
        long active = applicationRepository.countByUserIdAndCurrentStatusNotIn(userId, List.of(Status.REJECTED, Status.WITHDRAWN));
        long applied = applicationRepository.countByUserIdAndCurrentStatus(userId, Status.APPLIED);
        long interviews = applicationRepository.countByUserIdAndCurrentStatusIn(userId, List.of(Status.HR_INTERVIEW, Status.FINAL_INTERVIEW, Status.TECHNICAL_INTERVIEW));
        long offers = applicationRepository.countByUserIdAndCurrentStatus(userId, Status.OFFER);

        return new ApplicationsStatsResponseDto(active, applied, interviews, offers);
    }

    @Transactional
    public ApplicationResponseDto updateApplicationStatus(Long userId, UpdateApplicationStatusRequestDto request) {
        Application application = applicationRepository.findByUserIdAndId(userId, request.applicationId()).orElseThrow(() -> new ResourceNotFoundException("Application not found"));

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
                application.getLastActivityAt(),
                application.getNotes(),
                application.getRequirements(),
                application.getWorkMode()
        );
    }
}
