CREATE TABLE lesson_tb (
        id BIGSERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT NOT NULL,
        url_video VARCHAR(255) NOT NULL,
        course_id BIGINT NOT NULL,
        FOREIGN KEY (course_id) REFERENCES course_tb (id)
);