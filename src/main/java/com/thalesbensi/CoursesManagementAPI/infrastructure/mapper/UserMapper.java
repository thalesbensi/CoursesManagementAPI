package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.UserDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.UserResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    UserResponseTemplateDTO toResponseDTO(User user);
}
