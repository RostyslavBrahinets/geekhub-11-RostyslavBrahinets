CREATE TABLE IF NOT EXISTS contacts
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(256) UNIQUE,
    phone     VARCHAR(256) UNIQUE,
    person_id INTEGER NOT NULL UNIQUE,
    CONSTRAINT fk_person
        FOREIGN KEY (person_id)
            REFERENCES person (id)
            ON DELETE CASCADE
);
