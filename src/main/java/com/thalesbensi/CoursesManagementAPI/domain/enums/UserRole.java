package com.thalesbensi.CoursesManagementAPI.domain.enums;

public enum UserRole {
    TEACHER("admin"),
    STUDENT("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
