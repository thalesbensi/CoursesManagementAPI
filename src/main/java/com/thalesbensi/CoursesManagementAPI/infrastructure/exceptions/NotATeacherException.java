package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.exceptions;

public class NotATeacherException extends RuntimeException {
    public NotATeacherException(String message) {
        super(message);
    }
}
