package com.thalesbensi.CoursesManagementAPI.mapper;

import com.thalesbensi.CoursesManagementAPI.dto.LessonDTO;
import com.thalesbensi.CoursesManagementAPI.model.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDTO toDTO(Lesson lesson);

    Lesson toLesson(LessonDTO lessonDTO);

}
