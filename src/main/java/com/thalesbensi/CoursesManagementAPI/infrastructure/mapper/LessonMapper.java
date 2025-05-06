package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDTO toDTO(Lesson lesson);

    @Mappings({@Mapping(target = "course.id", source = "course")})
    Lesson requestDTOtoEntity(LessonRequestDTO lessonRequestDTO);
}
