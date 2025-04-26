package com.thalesbensi.CoursesManagementAPI.controller;

import com.thalesbensi.CoursesManagementAPI.dto.request.CourseRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.services.CourseService;
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
    public ResponseEntity<List<CourseResponseTemplateDTO>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @PostMapping()
    public ResponseEntity<CourseResponseTemplateDTO> createCourse(@RequestBody CourseRequestTemplateDTO course) {
        CourseResponseTemplateDTO createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseTemplateDTO> updateCourse(@PathVariable Long id, @RequestBody CourseRequestTemplateDTO courseDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id, courseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
