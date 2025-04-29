package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course course);

    Course toEntity(CourseRequestTemplateDTO courseRequestTemplateDTO);

    @Mapping(source = "teacher.name", target = "teacherName")
    CourseResponseTemplateDTO ResponseTemplateDTO(Course course);
}
