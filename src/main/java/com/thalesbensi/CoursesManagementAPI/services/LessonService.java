package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.Lesson.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.model.Lesson;
import com.thalesbensi.CoursesManagementAPI.repositories.LessonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    public LessonService(LessonRepository lessonRepository) {this.lessonRepository = lessonRepository;}

    public List<LessonDTO> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map(LessonDTO::fromEntity).toList();
    }

    public LessonDTO getLessonById(@Valid Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found! :("));
        return LessonDTO.fromEntity(lesson);
    }

    public LessonDTO createLesson(@Valid Lesson lesson) {
        lessonRepository.save(lesson);
        return LessonDTO.fromEntity(lesson);
    }

    public LessonDTO updateLesson(@Valid Lesson lesson, Long id) {
        Lesson lessonToBeUpdated = lessonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID " + id + " not found! :("));
        lessonToBeUpdated.setTitle(lesson.getTitle());
        lessonToBeUpdated.setDescription(lesson.getDescription());
        lessonToBeUpdated.setUrlVideo(lesson.getUrlVideo());
        lessonToBeUpdated.setCourse(lesson.getCourse());
        lessonRepository.save(lessonToBeUpdated);
        return LessonDTO.fromEntity(lessonToBeUpdated);
    }

    public void deleteLesson(@Valid Long id) {
        lessonRepository.deleteById(id);
    }

}
