package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.request.CourseRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.mapper.CourseMapper;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.User;
import com.thalesbensi.CoursesManagementAPI.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseResponseTemplateDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toCourseResponseTemplateDTO).toList();
    }

    public CourseResponseTemplateDTO getCourseById(Long id) {
        Course course =courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with ID:" + id + "not found! :("));
        return courseMapper.toCourseResponseTemplateDTO(course);
    }

    public CourseResponseTemplateDTO createCourse(CourseRequestTemplateDTO courseDTO) {
         userRepository.findById(courseDTO.teacherId())
                 .orElseThrow(() -> new RuntimeException("Teacher with ID: " + courseDTO.teacherId() + "not found! :( "));

        Course course = courseMapper.toEntity(courseDTO);
        course.setTeacher(userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher with ID: " + courseDTO.teacherId() + "not found! :( ")));
        courseRepository.save(course);
        return courseMapper.toCourseResponseTemplateDTO(course);
    }

    public CourseResponseTemplateDTO updateCourse(Long id, CourseRequestTemplateDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with ID: " + courseDTO.teacherId() + "not found! :( "));

        User teacher = userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher with ID: " + courseDTO.teacherId() + "not found! :( "));

        Course courseUpdated = courseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return courseMapper.toCourseResponseTemplateDTO(courseUpdated);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lesson with ID " + id + " not found! :(");
        }
        courseRepository.deleteById(id);
    }
}
