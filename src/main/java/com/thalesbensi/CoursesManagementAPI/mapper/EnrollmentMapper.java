package com.thalesbensi.CoursesManagementAPI.mapper;

import com.thalesbensi.CoursesManagementAPI.dto.EnrollmentDTO;
import com.thalesbensi.CoursesManagementAPI.dto.request.EnrollmentRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.EnrollmentResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.Enrollment;
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
