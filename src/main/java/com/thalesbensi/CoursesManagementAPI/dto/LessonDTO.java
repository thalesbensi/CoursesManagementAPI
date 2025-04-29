package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.dto.response.CourseResponseTemplateDTO;

public record LessonDTO(Long id, String title, String description, String urlVideo, CourseResponseTemplateDTO course) { }
