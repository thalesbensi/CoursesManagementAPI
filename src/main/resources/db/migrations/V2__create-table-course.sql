CREATE TABLE course_tb (
        id BIGSERIAL PRIMARY KEY,
        title VARCHAR(255) UNIQUE NOT NULL,
        description TEXT,
        teacher_id BIGINT NOT NULL,
        created_at DATE NOT NULL,
        FOREIGN KEY (teacher_id) REFERENCES user_tb (id)
);