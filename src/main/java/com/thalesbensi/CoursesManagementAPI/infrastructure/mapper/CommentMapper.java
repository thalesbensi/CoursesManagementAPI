package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.CommentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "student.name", target = "userName")
    @Mapping(source = "lesson.title", target = "lesson")
    CommentResponseDTO toCommentResponseDTO(Comment comment);




}
