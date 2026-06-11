package com.clara.jobtracker.application;

import com.clara.jobtracker.application.dto.ApplicationResponseDto;
import com.clara.jobtracker.application.dto.CreateApplicationRequestDto;
import com.clara.jobtracker.application.dto.UpdateApplicationStatusRequestDto;
import com.clara.jobtracker.applicationStatusHistory.ApplicationStatusHistory;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Transactional
    public ApplicationResponseDto createApplication(CreateApplicationRequestDto request) {
        LocalDate dateApplied = request.dateApplied();

        if (request.dateApplied() == null) {
            dateApplied = LocalDate.now();
        }

        ApplicationStatusHistory history = new ApplicationStatusHistory(request.dateApplied(), request.currentStatus());

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

        newApplication.addStatusHistory(history);

        Application savedApplication = applicationRepository.save(newApplication);
        return toResponse(savedApplication);
    }

    public List<ApplicationResponseDto> getAllApplications() {
        return applicationRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ApplicationResponseDto getApplicationById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));

        return toResponse(application);
    }

    @Transactional
    public ApplicationResponseDto updateApplicationStatus(UpdateApplicationStatusRequestDto request) {
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
