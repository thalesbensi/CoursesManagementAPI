package com.thalesbensi.CoursesManagementAPI.repositories;

import com.thalesbensi.CoursesManagementAPI.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
