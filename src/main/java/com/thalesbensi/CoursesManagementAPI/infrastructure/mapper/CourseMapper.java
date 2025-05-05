package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CourseRequestDTO courseRequestTemplateDTO);

    @Mapping(source = "teacher.name", target = "teacherName")
    CourseResponseDTO toResponseDTO(Course course);
}
