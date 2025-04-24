package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.User;

import java.util.Date;

public record CourseDTO(Long id, String title, String description, User teacher, Date creationDate) {

    public static CourseDTO fromEntity(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTeacher(),
                course.getCreationDate());
    }
}
