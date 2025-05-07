CREATE TABLE comment_tb (
        id BIGSERIAL PRIMARY KEY,
        content TEXT NOT NULL,
        student_id BIGINT NOT NULL,
        lesson_id BIGINT NOT NULL,
        comment_date DATE NOT NULL,
        FOREIGN KEY (student_id) REFERENCES user_tb (id),
        FOREIGN KEY (lesson_id) REFERENCES lesson_tb (id)
);