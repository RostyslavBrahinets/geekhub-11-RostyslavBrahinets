CREATE TABLE IF NOT EXISTS homework
(
    id         SERIAL PRIMARY KEY,
    task       VARCHAR(256) NOT NULL,
    deadline   TIMESTAMP    NOT NULL,
    lection_id INTEGER      NOT NULL,
    CONSTRAINT fk_lection
        FOREIGN KEY (lection_id)
            REFERENCES lection (id)
            ON DELETE CASCADE
);
