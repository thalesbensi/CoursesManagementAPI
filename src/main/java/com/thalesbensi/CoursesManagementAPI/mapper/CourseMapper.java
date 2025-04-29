package com.thalesbensi.CoursesManagementAPI.mapper;

import com.thalesbensi.CoursesManagementAPI.dto.CourseDTO;
import com.thalesbensi.CoursesManagementAPI.dto.request.CourseRequestTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.CourseResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course course);

    Course toEntity(CourseRequestTemplateDTO courseRequestTemplateDTO);

    @Mapping(source = "teacher.name", target = "teacherName")
    CourseResponseTemplateDTO ResponseTemplateDTO(Course course);
}
