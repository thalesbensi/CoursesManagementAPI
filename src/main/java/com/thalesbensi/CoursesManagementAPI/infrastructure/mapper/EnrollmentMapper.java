package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.EnrollmentDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.EnrollmentRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.EnrollmentResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentDTO toDTO(Enrollment enrollment);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "courseId", target = "course.id")
    Enrollment toEntity(EnrollmentRequestTemplateDTO enrollmentRequestTemplateDTO);

    EnrollmentResponseTemplateDTO toResponseDTO(Enrollment enrollment);


    EnrollmentResponseTemplateDTO fromRequestToResponseDTO(EnrollmentRequestTemplateDTO enrollmentRequestTemplateDTO);
}
