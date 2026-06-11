package com.clara.jobtracker.applicationStatusHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationStatusHistoryRepository extends JpaRepository<ApplicationStatusHistory, Long> {

    List<ApplicationStatusHistory> findByApplicationId(Long applicationId);
}
