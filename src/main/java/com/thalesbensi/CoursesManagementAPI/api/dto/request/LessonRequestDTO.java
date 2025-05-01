package com.thalesbensi.CoursesManagementAPI.api.dto.request;

public record LessonRequestDTO(String title, String description, String urlVideo, Long course) {
}
