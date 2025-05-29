package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.comment.CommentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.comment.CommentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Comment;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Lesson;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CommentRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.LessonRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.CommentMapper;
import com.thalesbensi.CoursesManagementAPI.infrastructure.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.thalesbensi.CoursesManagementAPI.infrastructure.utils.OwnershipVerifier.commentOwnershipVerifier;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, LessonRepository lessonRepository, UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponseDTO> getCommentsOnLesson(Long lessonId) {
        List<Comment> comments = commentRepository.findCommentsByLesson_Id(lessonId);
        return comments.stream().map(commentMapper::toCommentResponseDTO).toList();
    }

    public CommentResponseDTO addCommentToLesson(Long lessonId, CommentRequestDTO commentDTO) {
        String userEmail = SecurityUtils.getAuthenticatedUserEmail();

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ResourceNotFoundException("Lesson with ID: " + lessonId + " not found! :("));

        User user = userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User with email: " + userEmail + " not found!"));

        Comment comment = new Comment();
        comment.setContent(commentDTO.content());
        comment.setLesson(lesson);
        comment.setStudent(user);

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.toCommentResponseDTO(savedComment);
    }

    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentDTO){
        String userEmail = SecurityUtils.getAuthenticatedUserEmail();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID: " + commentId + " not found!"));

        commentOwnershipVerifier(comment, userEmail);

        Date date = new Date();
        comment.setContent(commentDTO.content());
        comment.setUpdatedAt(date);

        Comment updatedComment = commentRepository.save(comment);

        return commentMapper.toCommentResponseDTO(updatedComment);
    }

    public void deleteComment(Long commentId) {
        String userEmail = SecurityUtils.getAuthenticatedUserEmail();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID: " + commentId + " not found! :("));

        commentOwnershipVerifier(comment, userEmail);

        commentRepository.delete(comment);
    }

}
