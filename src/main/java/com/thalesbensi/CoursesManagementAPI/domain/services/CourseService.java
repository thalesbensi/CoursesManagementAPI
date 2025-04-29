package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.exceptions.NotATeacherException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.CourseMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
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
                .map(courseMapper::ResponseTemplateDTO).toList();
    }

    public CourseResponseTemplateDTO getCourseById(Long id) {
        Course course =courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with ID:" + id + "not found! :("));
        return courseMapper.ResponseTemplateDTO(course);
    }

    public CourseResponseTemplateDTO createCourse(CourseRequestTemplateDTO courseDTO) {
        userVerifier(courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        course.setTeacher(userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher with ID: " + courseDTO.teacherId() + "not found! :( ")));
        courseRepository.save(course);
        return courseMapper.ResponseTemplateDTO(course);
    }

    public CourseResponseTemplateDTO updateCourse(Long id, CourseRequestTemplateDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course with ID: " + courseDTO.teacherId() + "not found! :( "));
        userVerifier(courseDTO);
        Course courseUpdated = courseMapper.toEntity(courseDTO);
        courseRepository.save(course);
        return courseMapper.ResponseTemplateDTO(courseUpdated);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lesson with ID " + id + " not found! :(");
        }
        courseRepository.deleteById(id);
    }

    private void userVerifier(CourseRequestTemplateDTO courseDTO) {
          User teacher = userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher with ID: " + courseDTO.teacherId() + "not found! :( "));

          if (!(teacher.getUserRole() == UserRole.TEACHER)) {
              throw new NotATeacherException("The User with ID " + courseDTO.teacherId() + " is not a teacher!");
          }
    }
}
