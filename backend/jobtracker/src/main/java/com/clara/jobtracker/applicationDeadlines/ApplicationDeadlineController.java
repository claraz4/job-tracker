package com.clara.jobtracker.applicationDeadlines;

import com.clara.jobtracker.applicationDeadlines.dto.CreateDeadlineRequestDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto;
import com.clara.jobtracker.applicationDeadlines.dto.DeadlineResponseDto;
import com.clara.jobtracker.applicationDeadlines.enums.DeadlineSort;
import com.clara.jobtracker.security.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deadlines")
public class ApplicationDeadlineController {

    private final ApplicationDeadlineService applicationDeadlineService;

    public ApplicationDeadlineController(ApplicationDeadlineService applicationDeadlineService) {
        this.applicationDeadlineService = applicationDeadlineService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DeadlineDetailsResponseDto> getAllDeadlines(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam(required = false, defaultValue = "CREATED") DeadlineSort sort
    ) {
        return applicationDeadlineService.getAllDeadlines(user.id(), sort);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DeadlineResponseDto createDeadline(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestBody CreateDeadlineRequestDto request
    ) {
        return applicationDeadlineService.createDeadline(user.id(), request);
    }

}
