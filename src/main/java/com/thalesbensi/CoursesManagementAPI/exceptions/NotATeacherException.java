package com.thalesbensi.CoursesManagementAPI.exceptions;

public class NotATeacherException extends RuntimeException {
    public NotATeacherException(String message) {
        super(message);
    }
}
