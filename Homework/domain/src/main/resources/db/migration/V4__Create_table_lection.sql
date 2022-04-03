CREATE TABLE IF NOT EXISTS lection
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(256) NOT NULL,
    describe      VARCHAR(512) NOT NULL,
    lecturer_id   INTEGER      NOT NULL,
    creation_date TIMESTAMP DEFAULT current_timestamp,
    course_id     INTEGER      NOT NULL,
    CONSTRAINT fk_lecturer
        FOREIGN KEY (lecturer_id)
            REFERENCES person (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
            REFERENCES course (id)
            ON DELETE CASCADE
);
