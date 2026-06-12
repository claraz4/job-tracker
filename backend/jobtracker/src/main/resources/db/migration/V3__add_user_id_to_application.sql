ALTER TABLE application
ADD COLUMN user_id BIGINT;

ALTER TABLE application
ADD CONSTRAINT fk_application_user
FOREIGN KEY (user_id)
REFERENCES app_user(id);