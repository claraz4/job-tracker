package com.clara.jobtracker.application;

import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserIdAndId(Long userId, Long applicationId);
    List<Application> findByUserIdAndDateAppliedGreaterThanEqualOrderByDateAppliedAsc(Long userId, LocalDate date);
    List<Application> findByUserIdOrderByDateAppliedDesc(Long userId);
    List<Application> findByUserIdOrderByLastActivityAtDesc(Long userId);

    long countByUserIdAndCurrentStatus(Long userId, Status currentStatus);
    long countByUserIdAndCurrentStatusNotIn(Long userId, List<Status> statuses);
    long countByUserIdAndCurrentStatusIn(Long userId, List<Status> statuses);
}
