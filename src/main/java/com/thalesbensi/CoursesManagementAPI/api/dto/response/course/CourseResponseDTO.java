package com.thalesbensi.CoursesManagementAPI.api.dto.response.course;

import java.util.Date;

public record CourseResponseDTO(Long id, String title, String description, String teacherName, Date creationDate) { }
