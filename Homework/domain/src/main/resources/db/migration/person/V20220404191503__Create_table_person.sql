CREATE TABLE IF NOT EXISTS person
(
    id               SERIAL PRIMARY KEY,
    first_name       VARCHAR(256) NOT NULL,
    last_name        VARCHAR(256) NOT NULL,
    git_hub_nickname VARCHAR(256) NOT NULL,
    role             VARCHAR(10)  NOT NULL,
    course_id        INTEGER      NOT NULL,
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
            REFERENCES course (id)
            ON DELETE CASCADE
);
