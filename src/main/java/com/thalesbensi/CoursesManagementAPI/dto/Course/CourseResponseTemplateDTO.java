package com.thalesbensi.CoursesManagementAPI.dto.Course;

import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.User;

import java.util.Date;

public record CourseResponseTemplateDTO(Long id, String title, String description, Long teacher_id, String teacherName, Date creationDate) {

    public static CourseResponseTemplateDTO fromEntity(Course course, User teacher) {
        return new CourseResponseTemplateDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                teacher.getId(),
                teacher.getName(),
                course.getCreationDate());
    }

    public static CourseResponseTemplateDTO fromEntityWithoutTeacher(Course course) {
        User teacher = course.getTeacher();
        return fromEntity(course, teacher);
    }


}
