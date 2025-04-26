package com.thalesbensi.CoursesManagementAPI.mapper;

import com.thalesbensi.CoursesManagementAPI.dto.UserDTO;
import com.thalesbensi.CoursesManagementAPI.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO ToDTO(User user);

    User FromDTO(UserDTO userDTO);
}
