package com.thalesbensi.CoursesManagementAPI.api.dto.response;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

public record UserMinResponseDTO(String name, String email, UserRole userRole) { }