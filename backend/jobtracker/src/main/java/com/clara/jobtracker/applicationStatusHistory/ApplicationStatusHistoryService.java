package com.clara.jobtracker.applicationStatusHistory;

import com.clara.jobtracker.application.ApplicationRepository;
import com.clara.jobtracker.applicationStatusHistory.dto.ApplicationStatusHistoryResponseDto;
import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationStatusHistoryService {

    private final ApplicationStatusHistoryRepository statusHistoryRepository;
    private final ApplicationRepository applicationRepository;

    public ApplicationStatusHistoryService(ApplicationStatusHistoryRepository statusHistoryRepository, ApplicationRepository applicationRepository) {
        this.statusHistoryRepository = statusHistoryRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<ApplicationStatusHistoryResponseDto> getHistoryForApplication(Long applicationId) {
        if (!applicationRepository.existsById(applicationId)) {
            throw new ResourceNotFoundException("Application not found with id: " + applicationId);
        }

        return statusHistoryRepository.findByApplicationId(applicationId).stream().map(this::toResponse).toList();
    }

    private ApplicationStatusHistoryResponseDto toResponse(ApplicationStatusHistory history) {
        return new ApplicationStatusHistoryResponseDto(
                history.getId(),
                history.getDate(),
                history.getStatus()
        );
    }
}
