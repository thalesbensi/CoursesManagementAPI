package com.thalesbensi.CoursesManagementAPI.api.dto;

import com.thalesbensi.CoursesManagementAPI.domain.entity.User;

import java.util.Date;

public record CourseDTO(Long id, String title, String description, User teacher, Date creationDate) { }
