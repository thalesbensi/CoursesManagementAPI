package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.LessonListItemResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.LessonRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.CourseMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.LessonMapper;
import com.thalesbensi.CoursesManagementAPI.infrastructure.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.thalesbensi.CoursesManagementAPI.infrastructure.utils.OwnershipVerifier.courseOwnershipVerifier;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final CourseMapper courseMapper;
    private final LessonMapper lessonMapper;

    public CourseService(CourseRepository courseRepository, UserRepository userRepository, LessonRepository lessonRepository, CourseMapper courseMapper, LessonMapper lessonMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.courseMapper = courseMapper;
        this.lessonMapper = lessonMapper;
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

    public List<LessonListItemResponseDTO> getCourseLessons(Long id) {
        List<Lesson> lessons =  lessonRepository
                .findLessonsByCourse_Id(id);

        return lessons.stream()
                .map(lessonMapper::fromLessonToLessonListItemResponseDTO)
                .toList();
    }


    public CourseResponseDTO createCourse(CourseRequestDTO courseDTO) {
        String teacherEmail = SecurityUtils.getAuthenticatedUserEmail();

        User teacher = userRepository.findUserByEmail(teacherEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found! :("));

        Course course = new Course();
        course.setTitle(courseDTO.title());
        course.setDescription(courseDTO.description());
        course.setTeacher(teacher);

        Course saved = courseRepository.save(course);

        return courseMapper.toResponseDTO(saved);
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseDTO) {
        String teacherEmail = SecurityUtils.getAuthenticatedUserEmail();

        User teacher = userRepository.findUserByEmail(teacherEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found! :("));

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " not found! :("));

        courseOwnershipVerifier(teacherEmail, existingCourse);
        existingCourse.setTitle(courseDTO.title());
        existingCourse.setDescription(courseDTO.description());
        existingCourse.setTeacher(teacher);

        courseRepository.save(existingCourse);

        return courseMapper.toResponseDTO(existingCourse);
    }

    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course with ID: " + id + " not found! :(");
        }

        courseRepository.deleteById(id);
    }
}

