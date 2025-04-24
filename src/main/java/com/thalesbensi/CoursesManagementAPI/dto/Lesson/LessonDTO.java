package com.thalesbensi.CoursesManagementAPI.dto.Lesson;

import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.Lesson;

public record LessonDTO(Long id, String title, String description, String urlVideo, Course course) {

    public static LessonDTO fromEntity(Lesson lesson) {
        return new LessonDTO(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getDescription(),
                lesson.getUrlVideo(),
                lesson.getCourse());
    }
}
