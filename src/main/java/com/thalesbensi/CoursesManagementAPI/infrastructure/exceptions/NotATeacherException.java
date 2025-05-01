package com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions;

public class NotATeacherException extends RuntimeException {
    public NotATeacherException(String message) {
        super(message);
    }
}
