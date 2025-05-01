package com.thalesbensi.CoursesManagementAPI.api.dto.response;

public record EnrollmentResponseDTO(Long id, UserResponseDTO student, CourseResponseDTO course) {
}
