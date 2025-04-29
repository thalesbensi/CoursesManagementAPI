package com.thalesbensi.CoursesManagementAPI.api.dto;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseTemplateDTO;

public record LessonDTO(Long id, String title, String description, String urlVideo, CourseResponseTemplateDTO course) { }
