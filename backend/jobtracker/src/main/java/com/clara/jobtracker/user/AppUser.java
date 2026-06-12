package com.clara.jobtracker.user;

import com.clara.jobtracker.application.Application;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String position;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Application> applications = new ArrayList<>();

    public AppUser() {}

    public AppUser(String username, String name, String position) {
        this.username = username;
        this.name = name;
        this.position = position;
    }

    public void addApplication(Application application) {
        applications.add(application);
        application.setUser(this);
    }

    public void removeApplication(Application application) {
        applications.remove(application);
        application.setUser(null);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
