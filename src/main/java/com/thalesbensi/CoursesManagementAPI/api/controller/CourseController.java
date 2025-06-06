package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.course.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.course.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.lesson.LessonListItemResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.services.CourseService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {this.courseService = courseService;}

    @GetMapping()
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonListItemResponseDTO>> getCourseLessons(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseLessons(id));
    }

    @PostMapping()
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody @Valid CourseRequestDTO course) {
        CourseResponseDTO createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @RequestBody @Valid CourseRequestDTO courseDTO) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
