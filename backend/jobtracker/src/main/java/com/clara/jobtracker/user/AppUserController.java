package com.clara.jobtracker.user;

import com.clara.jobtracker.user.dto.AppUserResponseDto;
import com.clara.jobtracker.user.dto.CreateAppUserRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUserResponseDto createUser(@Valid @RequestBody CreateAppUserRequestDto request) {
        return appUserService.createUser(request);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserResponseDto> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserResponseDto getUserById(@PathVariable Long id) {
        return appUserService.getUserById(id);
    }
}
