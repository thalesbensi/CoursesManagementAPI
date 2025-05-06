package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.LessonMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
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

    public LessonDTO createLesson(LessonRequestDTO lessonDTO) {
        courseRepository.findById(lessonDTO.course())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for lesson creation."));
        Lesson lesson = lessonMapper.requestDTOtoEntity(lessonDTO);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toDTO(savedLesson);
    }

    public LessonDTO updateLesson(LessonDTO lessonDTO, Long id) {
        Lesson lessonToBeUpdated = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found."));

        Course course = courseRepository.findById(lessonDTO.course().id())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for lesson update."));

        lessonToBeUpdated.setTitle(lessonDTO.title());
        lessonToBeUpdated.setDescription(lessonDTO.description());
        lessonToBeUpdated.setUrlVideo(lessonDTO.urlVideo());
        lessonToBeUpdated.setCourse(course);

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
