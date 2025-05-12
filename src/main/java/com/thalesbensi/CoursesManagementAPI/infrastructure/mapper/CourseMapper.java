package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "teacher", source = "teacher")
    Course toEntity(CourseRequestDTO courseRequestDTO, User teacher);

    @Mapping(source = "teacher.name", target = "teacherName")
    CourseResponseDTO toResponseDTO(Course course);
}
