package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseCreationTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.User;
import com.thalesbensi.CoursesManagementAPI.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {


    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseDTO::fromEntity).toList();
    }

    public CourseDTO getCourseById(Long id) {
        Course course =courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with ID:" + id + "not found! :("));
        return CourseDTO.fromEntity(course);
    }

    public CourseResponseTemplateDTO createCourse(CourseCreationTemplateDTO courseDTO) {
        User teacher = userRepository.findById(courseDTO.teacherId()).orElseThrow(() -> new RuntimeException("Teacher with ID:" + courseDTO.teacherId() + "not found! :( "));
        Course course = ParseDTOToCourse(courseDTO, teacher);
        courseRepository.save(course);
        return CourseResponseTemplateDTO.fromEntity(course, teacher);
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

    private Course ParseDTOToCourse(CourseCreationTemplateDTO courseDTO, User teacher) {
        Course course = new Course();
        course.setTitle(courseDTO.title());
        course.setDescription(courseDTO.description());
        course.setTeacher(teacher);
        course.setCreationDate(new Date());
        return course;
    }
}
