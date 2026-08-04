package com.clara.jobtracker.applicationDeadlines;

import com.clara.jobtracker.application.Application;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "deadline")
public class ApplicationDeadline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = true, length = 250)
    private String details;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime dueAt;

    @Column(nullable = false)
    private boolean completed = false;

    public ApplicationDeadline() {}

    public ApplicationDeadline(
            Application application,
            String title,
            String details,
            LocalDateTime dueAt
    ) {
        this.application = application;
        this.title = title;
        this.details = details;
        this.dueAt = dueAt;
        this.createdAt = LocalDateTime.now();
        this.completed = false;
    }

    public Long getId() {
        return id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDateTime dueAt) {
        this.dueAt = dueAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
