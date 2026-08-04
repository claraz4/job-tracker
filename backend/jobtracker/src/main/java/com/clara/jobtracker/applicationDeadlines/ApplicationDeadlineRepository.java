package com.clara.jobtracker.applicationDeadlines;

import com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationDeadlineRepository extends JpaRepository<ApplicationDeadline, Long> {

    @Query("""
        SELECT new com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto(
                d.id, a.id, d.createdAt, d.title, d.details, d.dueAt, d.completed, a.position, a.company
            )
            FROM ApplicationDeadline d
                JOIN d.application a
                    WHERE a.user.id = :userId
                        ORDER BY d.createdAt DESC
    """)
    List<DeadlineDetailsResponseDto> findAllDetailedDeadlinesOrderByCreatedAtDesc(Long userId);

    @Query("""
        SELECT new com.clara.jobtracker.applicationDeadlines.dto.DeadlineDetailsResponseDto(
                d.id, a.id, d.createdAt, d.title, d.details, d.dueAt, d.completed, a.position, a.company
            )
            FROM ApplicationDeadline d
                JOIN d.application a
                    WHERE a.user.id = :userId
                        ORDER BY d.dueAt DESC
    """)
    List<DeadlineDetailsResponseDto> findAllDetailedDeadlinesOrderByDueAtDesc(Long userId);
}
