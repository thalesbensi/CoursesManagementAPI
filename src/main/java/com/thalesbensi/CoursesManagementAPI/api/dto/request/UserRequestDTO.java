package com.thalesbensi.CoursesManagementAPI.api.dto.request;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

public record UserRequestDTO(String name, String email, String password, UserRole userRole) { }
