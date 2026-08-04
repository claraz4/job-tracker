package com.clara.jobtracker.applicationDeadlines;

import com.clara.jobtracker.applicationDeadlines.dto.CreateDeadlineRequestDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineResponseDto;
import com.clara.jobtracker.applicationDeadlines.enums.DeadlineSort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/deadlines")
public class ApplicationDeadlineController {

    private final ApplicationDeadlineService applicationDeadlineService;

    public ApplicationDeadlineController(ApplicationDeadlineService applicationDeadlineService) {
        this.applicationDeadlineService = applicationDeadlineService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DeadlineDetailsResponseDto> getAllDeadlines(@PathVariable Long userId, @RequestParam(required = false, defaultValue = "CREATED") DeadlineSort sort) {
        return applicationDeadlineService.getAllDeadlines(userId, sort);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DeadlineResponseDto createDeadline(@PathVariable Long userId, @Valid @RequestBody CreateDeadlineRequestDto request) {
        return applicationDeadlineService.createDeadline(userId, request);
    }

}
