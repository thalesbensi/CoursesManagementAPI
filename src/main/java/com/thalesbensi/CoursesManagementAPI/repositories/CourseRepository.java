package com.thalesbensi.CoursesManagementAPI.repositories;

import com.thalesbensi.CoursesManagementAPI.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
