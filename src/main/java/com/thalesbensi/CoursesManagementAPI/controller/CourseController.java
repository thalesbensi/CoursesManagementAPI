package com.thalesbensi.CoursesManagementAPI.controller;

import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseCreationTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.dto.Course.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import com.thalesbensi.CoursesManagementAPI.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {this.courseService = courseService;}

    @GetMapping()
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @PostMapping()
    public ResponseEntity<CourseResponseTemplateDTO> createCourse(@RequestBody CourseCreationTemplateDTO course) {
        CourseResponseTemplateDTO createdCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
