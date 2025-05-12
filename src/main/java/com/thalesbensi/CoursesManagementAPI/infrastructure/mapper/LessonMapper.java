package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.LessonListItemResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "course", source = "course")
    LessonDTO toDTO(Lesson lesson);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "title", source = "lessonRequestDTO.title")
    @Mapping(target = "description", source = "lessonRequestDTO.description")
    @Mapping(target = "urlVideo", source = "lessonRequestDTO.urlVideo")
    Lesson requestDTOtoEntity(LessonRequestDTO lessonRequestDTO, Course course);

    LessonListItemResponseDTO fromLessonToLessonListItemResponseDTO(Lesson lesson);
}
