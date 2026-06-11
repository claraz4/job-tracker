package com.clara.jobtracker.applicationStatusHistory;

import com.clara.jobtracker.application.Application;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "application_status_history")
public class ApplicationStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public ApplicationStatusHistory() {}

    public ApplicationStatusHistory(LocalDate date, Status status) {
        this.date = date;
        this.status = status;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
