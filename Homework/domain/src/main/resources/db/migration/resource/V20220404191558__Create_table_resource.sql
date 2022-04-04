CREATE TABLE IF NOT EXISTS resource
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(256) NOT NULL,
    type       VARCHAR(5)   NOT NULL,
    data       VARCHAR(512) NOT NULL,
    lection_id INTEGER      NOT NULL,
    CONSTRAINT fk_lection
        FOREIGN KEY (lection_id)
            REFERENCES lection (id)
            ON DELETE CASCADE
);
