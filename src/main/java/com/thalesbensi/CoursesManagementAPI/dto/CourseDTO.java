package com.thalesbensi.CoursesManagementAPI.dto;

import com.thalesbensi.CoursesManagementAPI.model.User;

import java.util.Date;

public record CourseDTO(Long id, String title, String description, User teacher, Date creationDate) { }
