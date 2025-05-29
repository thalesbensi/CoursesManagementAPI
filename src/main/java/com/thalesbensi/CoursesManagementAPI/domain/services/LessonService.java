package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.lesson.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.lesson.LessonUpdateRequestDTO;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.LessonMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.LessonRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.utils.OwnershipVerifier;
import com.thalesbensi.CoursesManagementAPI.infrastructure.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;
    private final UserRepository userRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository, LessonMapper lessonMapper, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
        this.userRepository = userRepository;
    }

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toDTO)
                .toList();
    }

    public LessonDTO getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found."));

        return lessonMapper.toDTO(lesson);
    }

    public LessonDTO createLesson(Long courseId, LessonRequestDTO lessonDTO){
        String teacherEmail = SecurityUtils.getAuthenticatedUserEmail();

        userRepository.findUserByEmail(teacherEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found! :("));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for lesson creation."));

        OwnershipVerifier.courseOwnershipVerifier(teacherEmail, course);

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.title());
        lesson.setDescription(lessonDTO.description());
        lesson.setUrlVideo(lessonDTO.urlVideo());
        lesson.setCourse(course);

        lessonRepository.save(lesson);
        return lessonMapper.toDTO(lesson);
    }

    @Transactional
    public LessonDTO updateLesson(Long lessonId, LessonUpdateRequestDTO lessonDTO){
        String teacherEmail = SecurityUtils.getAuthenticatedUserEmail();

        userRepository.findUserByEmail(teacherEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found! :("));

        Lesson lessonToBeUpdated = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + lessonId + " not found."));

        OwnershipVerifier.courseOwnershipVerifier(teacherEmail, lessonToBeUpdated.getCourse());

        lessonToBeUpdated.setTitle(lessonDTO.title());
        lessonToBeUpdated.setDescription(lessonDTO.description());
        lessonToBeUpdated.setUrlVideo(lessonDTO.urlVideo());

        Lesson updatedLesson = lessonRepository.save(lessonToBeUpdated);
        return lessonMapper.toDTO(updatedLesson);
    }

    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lesson with ID " + id + " not found.");
        }
        lessonRepository.deleteById(id);
    }
}
