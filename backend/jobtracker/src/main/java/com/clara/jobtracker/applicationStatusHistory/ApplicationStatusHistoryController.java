package com.clara.jobtracker.applicationStatusHistory;

import com.clara.jobtracker.applicationStatusHistory.dto.ApplicationStatusHistoryResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/applications/{applicationId}/status-history")
public class ApplicationStatusHistoryController {

    private final ApplicationStatusHistoryService statusHistoryService;

    public ApplicationStatusHistoryController(ApplicationStatusHistoryService statusHistoryService) {
        this.statusHistoryService = statusHistoryService;
    }

    @GetMapping("")
    public List<ApplicationStatusHistoryResponseDto> getHistoryForApplication(@PathVariable Long applicationId, @PathVariable Long userId) {
        return statusHistoryService.getHistoryForApplication(userId, applicationId);
    }
}
