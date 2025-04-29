package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.request.LessonRequestDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDTO toDTO(Lesson lesson);

    Lesson toEntity(LessonDTO lessonDTO);

    Lesson RequestDTOtoEntity(LessonRequestDTO lessonRequestDTO);

}
