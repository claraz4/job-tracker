CREATE TABLE deadline (
  id BIGSERIAL PRIMARY KEY,

  application_id BIGINT NOT NULL,

  created_at TIMESTAMP NOT NULL,

  title VARCHAR(100) NOT NULL,

  details VARCHAR(250),

  due_at TIMESTAMP NOT NULL,

  completed BOOLEAN NOT NULL DEFAULT FALSE,

  CONSTRAINT fk_deadline_application
      FOREIGN KEY (application_id)
          REFERENCES application(id)
          ON DELETE CASCADE
);