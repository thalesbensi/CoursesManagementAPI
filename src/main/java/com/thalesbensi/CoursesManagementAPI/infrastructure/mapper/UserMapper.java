package com.thalesbensi.CoursesManagementAPI.infrastructure.mapper;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.user.UserRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO user);

    UserResponseDTO toResponseDTO(User user);

    UserMinResponseDTO fromUserToMinResponseDTO(User user);
}
