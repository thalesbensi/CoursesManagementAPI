package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.EnrollmentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.EnrollmentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "courseId", target = "course.id")
    Enrollment toEntity(EnrollmentRequestDTO enrollmentRequestDTO);

    EnrollmentResponseDTO toResponseDTO(Enrollment enrollment);
}
