--changeset  michalenok:1.0.0
CREATE TABLE IF NOT EXISTS sensor_types (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(20) NOT NULL UNIQUE
);

INSERT INTO sensor_types (name) VALUES
                                    ('Pressure'),
                                    ('Voltage'),
                                    ('Temperature'),
                                    ('Humidity');

--rollback DROP TABLE sensor_types;

--changeset michalenok:1.0.1
CREATE TABLE IF NOT EXISTS units (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO units (name) VALUES
                             ('bar'),
                             ('voltage'),
                             ('°C'),
                             ('%');

--rollback DROP TABLE units;

--changeset michalenok:1.0.2
CREATE TABLE IF NOT EXISTS sensors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(30) NOT NULL CHECK (LENGTH(name) >= 3),
                         model VARCHAR(15) NOT NULL,
                         type_id INT NOT NULL,
                         range_from INT NOT NULL CHECK (range_from > 0),
                         range_to INT NOT NULL CHECK (range_to > range_from),
                         unit_id INT,
                         location VARCHAR(40),
                         description VARCHAR(200),
                         FOREIGN KEY (type_id) REFERENCES sensor_types(id),
                         FOREIGN KEY (unit_id) REFERENCES units(id)
);

--rollback DROP TABLE sensors;