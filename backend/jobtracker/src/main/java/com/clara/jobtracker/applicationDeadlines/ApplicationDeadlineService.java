package com.clara.jobtracker.applicationDeadlines;

import com.clara.jobtracker.application.Application;
import com.clara.jobtracker.application.ApplicationRepository;
import com.clara.jobtracker.applicationDeadlines.dto.CreateDeadlineRequestDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineResponseDto;
import com.clara.jobtracker.applicationDeadlines.enums.DeadlineSort;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import com.clara.jobtracker.user.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationDeadlineService {

    private final ApplicationDeadlineRepository applicationDeadlineRepository;
    private final AppUserRepository appUserRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationDeadlineService(ApplicationDeadlineRepository applicationDeadlineRepository, AppUserRepository appUserRepository, ApplicationRepository applicationRepository) {
        this.applicationDeadlineRepository = applicationDeadlineRepository;
        this.appUserRepository = appUserRepository;
        this.applicationRepository = applicationRepository;
    }

    public DeadlineResponseDto createDeadline(Long userId, CreateDeadlineRequestDto request) {
        Application application = applicationRepository
                .findByUserIdAndId(userId, request.applicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: %d and user id: %d".formatted(request.applicationId(), userId)));

        ApplicationDeadline deadline = new ApplicationDeadline(
                application,
                request.title(),
                request.details(),
                request.dueAt()
        );

        ApplicationDeadline savedDeadline = applicationDeadlineRepository.save(deadline);
        return toResponse(savedDeadline);
    }

    public List<DeadlineDetailsResponseDto> getAllDeadlines(Long userId, DeadlineSort sort) {
        appUserRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<DeadlineDetailsResponseDto> deadlines = switch (sort) {
            case CREATED -> applicationDeadlineRepository.findAllDetailedDeadlinesOrderByCreatedAtDesc(userId);
            case DEADLINE_DESC -> applicationDeadlineRepository.findAllDetailedDeadlinesOrderByDueAtDesc(userId);
        };

        return deadlines;
    }

    private DeadlineResponseDto toResponse(ApplicationDeadline deadline) {
        return new DeadlineResponseDto(
                deadline.getId(),
                deadline.getApplication().getId(),
                deadline.getCreatedAt(),
                deadline.getTitle(),
                deadline.getDetails(),
                deadline.getDueAt(),
                deadline.isCompleted()
        );
    }
}
