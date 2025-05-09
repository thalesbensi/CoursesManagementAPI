CREATE TABLE user_tb (
        id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
        name VARCHAR(255) NOT NULL ,
        email VARCHAR(255) UNIQUE NOT NULL,
        password VARCHAR(255) NOT NULL,
        role VARCHAR(10) CHECK (role IN ('TEACHER', 'STUDENT', 'ADMIN'))
);