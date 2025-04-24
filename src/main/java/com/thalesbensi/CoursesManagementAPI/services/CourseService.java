package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {this.courseRepository = courseRepository;}

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseDTO::fromEntity).toList();
    }

    public CourseDTO getCourseById(Long id) {
        Course course =courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with ID:" + id + "not found! :("));
        return CourseDTO.fromEntity(course);
    }

    public CourseDTO createCourse(Course course) {
        courseRepository.save(course);
        return CourseDTO.fromEntity(course);
    }

    public CourseDTO updateCourse(Long id, Course course) {
        Course courseToBeUpdated = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with ID:" + id + "not found! :("));
        courseToBeUpdated.setTitle(course.getTitle());
        courseToBeUpdated.setDescription(course.getDescription());
        courseToBeUpdated.setTeacher(course.getTeacher());
        courseRepository.save(courseToBeUpdated);
        return CourseDTO.fromEntity(courseToBeUpdated);
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
