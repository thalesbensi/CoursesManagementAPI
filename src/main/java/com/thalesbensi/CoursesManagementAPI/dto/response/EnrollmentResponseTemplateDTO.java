package com.thalesbensi.CoursesManagementAPI.dto.response;

public record EnrollmentResponseTemplateDTO(Long id, UserResponseTemplateDTO student, CourseResponseTemplateDTO course) {
}
