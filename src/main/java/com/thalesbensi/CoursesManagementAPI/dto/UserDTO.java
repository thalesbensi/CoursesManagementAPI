package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.enums.UserRole;
import com.thalesbensi.CoursesManagementAPI.model.User;


public record UserDTO(Long userId, String userName, String email, String password, UserRole userRole) {

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserRole());
    }
}
