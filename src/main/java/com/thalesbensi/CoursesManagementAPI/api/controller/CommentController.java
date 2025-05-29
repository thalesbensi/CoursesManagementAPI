package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.comment.CommentRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.comment.CommentResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("lesson/{lessonId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsOnLesson(@PathVariable Long lessonId){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsOnLesson(lessonId));
    }

    @PostMapping("lesson/{lessonId}")
    public ResponseEntity addCommentToLesson(@PathVariable Long lessonId, @RequestBody @Valid CommentRequestDTO commentRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addCommentToLesson(lessonId, commentRequestDTO));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity updateLesson(@PathVariable Long commentId, @RequestBody @Valid CommentRequestDTO commentRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, commentRequestDTO));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
