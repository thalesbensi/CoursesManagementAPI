package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.enums.UserRole;



public record UserDTO(Long userId, String userName, String email, String password, UserRole userRole) { }

