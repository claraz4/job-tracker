CREATE TABLE refresh_token (
   id BIGSERIAL PRIMARY KEY,

   token VARCHAR(255) NOT NULL UNIQUE,

   user_id BIGINT NOT NULL UNIQUE,

   expiry_date TIMESTAMP WITH TIME ZONE NOT NULL,

   CONSTRAINT fk_refresh_token_user
       FOREIGN KEY (user_id)
           REFERENCES app_user(id)
           ON DELETE CASCADE
);

ALTER TABLE app_user
ALTER COLUMN password SET NOT NULL;