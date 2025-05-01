package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.EnrollmentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.EnrollmentResponseDTO;
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

    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentMapper enrollmentMapper, UserRepository userRepository, CourseRepository courseRepository) {
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
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment with ID:" + id + "not found :("));
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public EnrollmentResponseDTO createEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
          userRepository.findById(enrollmentRequestDTO.studentId())
                  .orElseThrow(() -> new RuntimeException("User with ID " + enrollmentRequestDTO.studentId() + " not found"));
          courseRepository.findById(enrollmentRequestDTO.courseId())
                  .orElseThrow(() -> new RuntimeException("Course with ID " + enrollmentRequestDTO.courseId() + " not found"));
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequestDTO);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public EnrollmentResponseDTO updateEnrollment(Long id, EnrollmentRequestDTO enrollmentDTO) {
        enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment with ID:" + id + "not found :("));
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentDTO);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment with ID " + id + " not found! :(");
        }
        enrollmentRepository.deleteById(id);
    }
}
