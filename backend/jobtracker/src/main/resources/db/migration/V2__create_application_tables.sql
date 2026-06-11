CREATE TABLE application (
     id BIGSERIAL PRIMARY KEY,
     position VARCHAR(50) NOT NULL,
     company VARCHAR(50) NOT NULL,
     location VARCHAR(100) NOT NULL,
     job_type VARCHAR(50) NOT NULL,
     priority VARCHAR(50) NOT NULL,
     current_status VARCHAR(50) NOT NULL,
     date_applied DATE,
     notes TEXT,
     requirements TEXT,
     work_mode VARCHAR(50) NOT NULL
);

CREATE TABLE application_status_history (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_application_status_history_application
        FOREIGN KEY (application_id)
            REFERENCES application(id)
            ON DELETE CASCADE
);