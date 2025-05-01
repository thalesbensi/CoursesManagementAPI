package com.thalesbensi.CoursesManagementAPI.api.dto.response;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

public record UserResponseDTO(Long id, String name, String email, String password, UserRole userRole) {
}
