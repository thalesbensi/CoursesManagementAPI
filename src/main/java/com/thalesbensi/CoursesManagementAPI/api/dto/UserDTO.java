package com.thalesbensi.CoursesManagementAPI.api.dto;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

public record UserDTO(Long id, String name, String email, String password, UserRole userRole) { }

