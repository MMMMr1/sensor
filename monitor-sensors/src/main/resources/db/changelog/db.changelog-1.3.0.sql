--changeset  michalenok:1.0.0

INSERT INTO users (id, username, password) VALUES ('1', 'admin', '$2a$10$HtffyemshMze9jRdkLq.p.TQ0KzvWRd1vQ4tvKAXRreWLzqOsBPbe'
);

INSERT INTO  user_authority_mapping (user_id, authority_id) VALUES ('1', '1');