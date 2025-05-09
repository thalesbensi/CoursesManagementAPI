CREATE TABLE enrollment_tb (
        id BIGSERIAL PRIMARY KEY,
        student_id BIGINT NOT NULL,
        course_id BIGINT NOT NULL,
        enrollment_date DATE NOT NULL,
        FOREIGN KEY (student_id) REFERENCES user_tb (id),
        FOREIGN KEY (course_id) REFERENCES course_tb (id)
);