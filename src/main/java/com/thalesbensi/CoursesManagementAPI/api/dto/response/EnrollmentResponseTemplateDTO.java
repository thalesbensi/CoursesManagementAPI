package com.thalesbensi.CoursesManagementAPI.api.dto.response;

public record EnrollmentResponseTemplateDTO(Long id, UserResponseTemplateDTO student, CourseResponseTemplateDTO course) {
}
