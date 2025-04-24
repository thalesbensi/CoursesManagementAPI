package com.thalesbensi.CoursesManagementAPI.dto.Lesson;


import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.Lesson;

public record LessonDTO(Long id, String title, String description, String urlVideo, CourseResponseTemplateDTO course) {
    public static LessonDTO fromEntity(Lesson lesson) {
        return new LessonDTO(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getUrlVideo(),
                CourseResponseTemplateDTO.fromEntityWithoutTeacher(lesson.getCourse())
        );
    }

}
