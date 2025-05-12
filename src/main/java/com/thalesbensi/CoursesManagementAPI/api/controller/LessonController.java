package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.domain.services.LessonService;
import org.apache.coyote.BadRequestException;
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

    @PostMapping("/{courseId}")
    public ResponseEntity<LessonDTO> createLesson(@PathVariable Long courseId, @RequestBody LessonRequestDTO lesson) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(courseId, lesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lesson) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.updateLesson(id, lesson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonDTO> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
