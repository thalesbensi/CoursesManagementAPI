package com.thalesbensi.CoursesManagementAPI.api.dto.authentication;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

public record RegisterDTO(String email, String password, String name, UserRole userRole) {
}
