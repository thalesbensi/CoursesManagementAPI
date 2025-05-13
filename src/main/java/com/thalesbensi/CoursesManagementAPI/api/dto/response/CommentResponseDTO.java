package com.thalesbensi.CoursesManagementAPI.api.dto.response;

import java.util.Date;

public record CommentResponseDTO(Long id, String content, String userName, String lesson, Date commentDate) {
}
