package com.thalesbensi.CoursesManagementAPI.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "comment_tb")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @NotNull
    private User student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @NotNull
    private Lesson lesson;

    @Column(name = "comment_date")
    private Date commentDate = new Date();

    @Column(name = "updated_at")
    private Date updatedAt = null;
}
