package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.EnrollmentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.EnrollmentResponseDTO;
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
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService) {this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    @GetMapping()
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.getEnrollmentById(id));
    }

    @PostMapping()
    public ResponseEntity<EnrollmentResponseDTO> createEnrollment(@RequestBody EnrollmentRequestDTO enrollmentDTO) {
        EnrollmentResponseDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentRequestDTO enrollmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.updateEnrollment(id, enrollmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
