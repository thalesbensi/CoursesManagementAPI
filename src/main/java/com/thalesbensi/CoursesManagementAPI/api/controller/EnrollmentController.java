package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.enrollment.EnrollmentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.services.CourseService;
import com.thalesbensi.CoursesManagementAPI.domain.services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping()
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
                             return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollmentById(id));
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@PathVariable Long courseId) {
        EnrollmentResponseDTO createdEnrollment = enrollmentService.createEnrollment(courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
