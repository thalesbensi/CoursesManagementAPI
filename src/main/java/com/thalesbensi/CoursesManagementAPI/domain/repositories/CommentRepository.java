package com.thalesbensi.CoursesManagementAPI.domain.repositories;

import com.thalesbensi.CoursesManagementAPI.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByLesson_Id(Long lessonId);
}
