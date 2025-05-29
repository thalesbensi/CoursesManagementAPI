package com.thalesbensi.CoursesManagementAPI.api.dto;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.course.CourseResponseDTO;

public record LessonDTO(Long id, String title, String description, String urlVideo, CourseResponseDTO course) { }
