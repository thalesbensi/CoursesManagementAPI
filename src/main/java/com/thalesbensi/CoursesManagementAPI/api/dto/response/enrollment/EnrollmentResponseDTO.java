package com.thalesbensi.CoursesManagementAPI.api.dto.response.enrollment;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.course.CourseResponseDTO;

public record EnrollmentResponseDTO(Long id, UserMinResponseDTO student, CourseResponseDTO course) {
}
