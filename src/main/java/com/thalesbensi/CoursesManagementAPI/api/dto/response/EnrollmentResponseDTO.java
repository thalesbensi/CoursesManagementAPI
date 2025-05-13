package com.thalesbensi.CoursesManagementAPI.api.dto.response;

public record EnrollmentResponseDTO(Long id, UserMinResponseDTO student, CourseResponseDTO course) {
}
