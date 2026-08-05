package com.clara.jobtracker.user;

import com.clara.jobtracker.common.exceptions.ResourceNotFoundException;
import com.clara.jobtracker.user.dto.AppUserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUserResponseDto> getAllUsers() {
        return appUserRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AppUserResponseDto getUserById(Long id) {
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        return toResponse(user);
    }

    private AppUserResponseDto toResponse(AppUser user) {
        return new AppUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPosition()
        );
    }
}
