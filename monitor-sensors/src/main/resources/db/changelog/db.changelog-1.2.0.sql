--changeset  michalenok:1.0.0
CREATE TABLE IF NOT EXISTS  authority (
                           id SERIAL PRIMARY KEY,
                           authority VARCHAR(50) UNIQUE NOT NULL
);
INSERT INTO  authority (authority) VALUES
                             ('ADMIN'),
                             ('VIEWER');
--rollback DROP TABLE authority;

--changeset  michalenok:2.0.0
CREATE TABLE IF NOT EXISTS  users (
                                      id SERIAL PRIMARY KEY,
                                      username VARCHAR(50) UNIQUE NOT NULL,
                                      password VARCHAR(255) NOT NULL
);
--rollback DROP TABLE users;

--changeset  michalenok:3.0.0
CREATE TABLE IF NOT EXISTS  user_authority_mapping (
                                        user_id BIGINT NOT NULL,
                                        authority_id BIGINT NOT NULL,
                                        CONSTRAINT fk_user_authority_user FOREIGN KEY (user_id) REFERENCES users(id),
                                        CONSTRAINT fk_user_authority_authority FOREIGN KEY (authority_id) REFERENCES authority(id)
);
--rollback DROP TABLE user_authority_mapping;