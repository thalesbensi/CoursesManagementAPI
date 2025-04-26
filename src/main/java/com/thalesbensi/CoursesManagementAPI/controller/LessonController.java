package com.thalesbensi.CoursesManagementAPI.controller;

import com.thalesbensi.CoursesManagementAPI.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {this.lessonService = lessonService;}

    @GetMapping()
    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getAllLessons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.getLessonById(id));
    }

    @PostMapping()
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lesson) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(lesson));
    }

    @PutMapping()
    public ResponseEntity<LessonDTO> updateLesson(@RequestBody LessonDTO lesson, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.updateLesson(lesson,id));
    }

    @DeleteMapping()
    public ResponseEntity<LessonDTO> deleteLesson(Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
