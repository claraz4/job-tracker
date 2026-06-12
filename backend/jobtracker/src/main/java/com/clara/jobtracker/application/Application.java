package com.clara.jobtracker.application;

import com.clara.jobtracker.application.enums.JobType;
import com.clara.jobtracker.application.enums.Priority;
import com.clara.jobtracker.applicationStatusHistory.ApplicationStatusHistory;
import com.clara.jobtracker.applicationStatusHistory.enums.Status;
import com.clara.jobtracker.application.enums.WorkMode;
import com.clara.jobtracker.user.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String position;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String company;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String location;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status currentStatus;

    private LocalDate dateApplied;

    private String notes;

    private String requirements;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkMode workMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @OneToMany(
            mappedBy = "application",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ApplicationStatusHistory> statusHistory = new ArrayList<>();

    public Application() {}

    public Application(
            String position,
            String company,
            String location,
            JobType jobType,
            Priority priority,
            Status currentStatus,
            LocalDate dateApplied,
            String notes,
            String requirements,
            WorkMode workMode
    ) {
        this.position = position;
        this.company = company;
        this.location = location;
        this.jobType = jobType;
        this.priority = priority;
        this.currentStatus = currentStatus;
        this.dateApplied = dateApplied;
        this.notes = notes;
        this.requirements = requirements;
        this.workMode = workMode;
    }

    public void addStatusHistory(ApplicationStatusHistory history) {
        statusHistory.add(history);
        history.setApplication(this);
        this.currentStatus = history.getStatus();
    }

    public void removeStatusHistory(ApplicationStatusHistory history) {
        statusHistory.remove(history);
        history.setApplication(null);
    }

    public Long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDate dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkMode workMode) {
        this.workMode = workMode;
    }

    public List<ApplicationStatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
