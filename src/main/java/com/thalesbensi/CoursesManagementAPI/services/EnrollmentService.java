package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.request.EnrollmentRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.EnrollmentResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.mapper.EnrollmentMapper;
import com.thalesbensi.CoursesManagementAPI.model.Enrollment;
import com.thalesbensi.CoursesManagementAPI.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.repositories.EnrollmentRepository;
import com.thalesbensi.CoursesManagementAPI.repositories.UserRepository;
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

    public List<EnrollmentResponseTemplateDTO> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollments.stream().map(enrollmentMapper::toResponseDTO).toList();
    }

    public EnrollmentResponseTemplateDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment with ID:" + id + "not found :("));
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public EnrollmentResponseTemplateDTO createEnrollment(EnrollmentRequestTemplateDTO enrollmentRequestTemplateDTO) {
          userRepository.findById(enrollmentRequestTemplateDTO.studentId())
                  .orElseThrow(() -> new RuntimeException("User with ID " + enrollmentRequestTemplateDTO.studentId() + " not found"));
          courseRepository.findById(enrollmentRequestTemplateDTO.courseId())
                  .orElseThrow(() -> new RuntimeException("Course with ID " + enrollmentRequestTemplateDTO.courseId() + " not found"));
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequestTemplateDTO);
        enrollmentRepository.save(enrollment);
        return enrollmentMapper.toResponseDTO(enrollment);
    }

    public EnrollmentResponseTemplateDTO updateEnrollment(Long id, EnrollmentRequestTemplateDTO enrollmentDTO) {
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
