package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.Lesson.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.model.Lesson;
import com.thalesbensi.CoursesManagementAPI.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.repositories.LessonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(LessonDTO::fromEntity)
                .toList();
    }

    public LessonDTO getLessonById(@Valid Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found! :("));
        return LessonDTO.fromEntity(lesson);
    }

    public LessonDTO createLesson(@Valid LessonDTO lessonDTO) {
        Course course = courseRepository.findById(lessonDTO.course().id())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for lesson creation"));

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.title());
        lesson.setDescription(lessonDTO.description());
        lesson.setUrlVideo(lessonDTO.urlVideo());
        lesson.setCourse(course);

        Lesson savedLesson = lessonRepository.save(lesson);
        return LessonDTO.fromEntity(savedLesson);
    }

    public LessonDTO updateLesson(@Valid LessonDTO lessonDTO, Long id) {
        Lesson lessonToBeUpdated = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found! :("));

        Course course = courseRepository.findById(lessonDTO.course().id())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for lesson update"));

        lessonToBeUpdated.setTitle(lessonDTO.title());
        lessonToBeUpdated.setDescription(lessonDTO.description());
        lessonToBeUpdated.setUrlVideo(lessonDTO.urlVideo());
        lessonToBeUpdated.setCourse(course);

        Lesson updatedLesson = lessonRepository.save(lessonToBeUpdated);
        return LessonDTO.fromEntity(updatedLesson);
    }

    public void deleteLesson(@Valid Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lesson with ID " + id + " not found! :(");
        }
        lessonRepository.deleteById(id);
    }
}
