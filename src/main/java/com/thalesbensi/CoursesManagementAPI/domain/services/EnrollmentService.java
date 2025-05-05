package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.EnrollmentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.EnrollmentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.EnrollmentMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Enrollment;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.EnrollmentRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper,
                             UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<EnrollmentResponseDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream().map(enrollmentMapper::toResponseDTO).toList();
    }

    public EnrollmentResponseDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment with ID:" + id + " not found :("));
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO dto) {
        User student = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + dto.studentId() + " not found"));
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course with ID " + dto.courseId() + " not found"));
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(saved);
    }

    public EnrollmentResponseDTO updateEnrollment(Long id, EnrollmentRequestDTO dto) {
        Enrollment existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment with ID:" + id + " not found :("));
        User student = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new RuntimeException("User with ID " + dto.studentId() + " not found"));
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course with ID " + dto.courseId() + " not found"));
        existing.setStudent(student);
        existing.setCourse(course);
        Enrollment updated = enrollmentRepository.save(existing);
        return enrollmentMapper.toResponseDTO(updated);
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment with ID " + id + " not found! :(");
        }
        enrollmentRepository.deleteById(id);
    }
}

