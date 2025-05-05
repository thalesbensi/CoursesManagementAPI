package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.NotATeacherException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
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

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .toList();
    }

    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " not found! :("));
        return courseMapper.toResponseDTO(course);
    }

    public CourseResponseDTO createCourse(CourseRequestDTO courseDTO) {
        userVerifier(courseDTO);
        Course course = courseMapper.toEntity(courseDTO);
        User teacher = userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with ID: " + courseDTO.teacherId() + " not found! :( "));
        course.setTeacher(teacher);
        courseRepository.save(course);
        return courseMapper.toResponseDTO(course);
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " not found! :("));
        userVerifier(courseDTO);
        existingCourse.setTitle(courseDTO.title());
        existingCourse.setDescription(courseDTO.description());
        existingCourse.setTeacher(userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with ID: " + courseDTO.teacherId() + " not found! :(")));
        courseRepository.save(existingCourse);
        return courseMapper.toResponseDTO(existingCourse);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course with ID: " + id + " not found! :(");
        }
        courseRepository.deleteById(id);
    }

    private void userVerifier(CourseRequestDTO courseDTO) {
        User teacher = userRepository.findById(courseDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher with ID: " + courseDTO.teacherId() + " not found! :( "));

        if (!(teacher.getUserRole() == UserRole.TEACHER)) {
            throw new NotATeacherException("The User with ID " + courseDTO.teacherId() + " is not a teacher!");
        }
    }
}

