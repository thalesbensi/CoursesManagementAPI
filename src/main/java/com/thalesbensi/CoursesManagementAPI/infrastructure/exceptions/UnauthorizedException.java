package com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
