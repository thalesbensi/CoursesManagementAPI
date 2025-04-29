package com.thalesbensi.CoursesManagementAPI.controller;

import com.thalesbensi.CoursesManagementAPI.dto.EnrollmentDTO;
import com.thalesbensi.CoursesManagementAPI.dto.request.EnrollmentRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.EnrollmentResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.services.CourseService;
import com.thalesbensi.CoursesManagementAPI.services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService) {this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<List<EnrollmentResponseTemplateDTO>> getAllEnrollments() {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseTemplateDTO> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollmentById(id));
    }

    @PostMapping()
    public ResponseEntity<EnrollmentResponseTemplateDTO> createEnrollment(@RequestBody EnrollmentRequestTemplateDTO enrollmentDTO) {
        EnrollmentResponseTemplateDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseTemplateDTO> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentRequestTemplateDTO enrollmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.updateEnrollment(id, enrollmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnrollmentResponseTemplateDTO> deleteEnrollment(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
