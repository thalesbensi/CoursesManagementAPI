package com.thalesbensi.CoursesManagementAPI.domain.repositories;

import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> { 
    List<Lesson> findLessonsByCourse_Id(Long courseId);
}
