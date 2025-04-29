package com.thalesbensi.CoursesManagementAPI.dto.request;

public record LessonRequestDTO(String title, String description, String urlVideo, Long courseId) {
}
