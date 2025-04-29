package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

