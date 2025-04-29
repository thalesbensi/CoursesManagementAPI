package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.enums.UserRole;

public record UserDTO(Long id, String name, String email, String password, UserRole userRole) { }

