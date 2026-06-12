package com.clara.jobtracker.applicationStatusHistory;

import com.clara.jobtracker.application.Application;
import com.clara.jobtracker.application.ApplicationRepository;
import com.clara.jobtracker.applicationStatusHistory.dto.ApplicationStatusHistoryResponseDto;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationStatusHistoryService {

    private final ApplicationRepository applicationRepository;

    public ApplicationStatusHistoryService(ApplicationStatusHistoryRepository statusHistoryRepository, ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<ApplicationStatusHistoryResponseDto> getHistoryForApplication(Long userId, Long applicationId) {
        Application application = applicationRepository.findByIdAndUserId(applicationId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id " + applicationId + " for user " + userId
                ));

        return application.getStatusHistory().stream().map(this::toResponse).toList();
    }

    private ApplicationStatusHistoryResponseDto toResponse(ApplicationStatusHistory history) {
        return new ApplicationStatusHistoryResponseDto(
                history.getId(),
                history.getDate(),
                history.getStatus()
        );
    }
}
