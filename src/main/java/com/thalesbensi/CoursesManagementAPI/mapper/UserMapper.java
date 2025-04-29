package com.thalesbensi.CoursesManagementAPI.mapper;

import com.thalesbensi.CoursesManagementAPI.dto.UserDTO;
import com.thalesbensi.CoursesManagementAPI.dto.response.UserResponseTemplateDTO;
import com.thalesbensi.CoursesManagementAPI.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    UserResponseTemplateDTO toResponseDTO(User user);
}
