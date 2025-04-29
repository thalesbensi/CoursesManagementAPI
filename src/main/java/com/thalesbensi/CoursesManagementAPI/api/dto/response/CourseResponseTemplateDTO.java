package com.thalesbensi.CoursesManagementAPI.api.dto.response;

import java.util.Date;

public record CourseResponseTemplateDTO(Long id, String title, String description, String teacherName, Date creationDate) { }
