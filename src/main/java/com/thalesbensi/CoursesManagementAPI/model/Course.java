package com.thalesbensi.CoursesManagementAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "course_tb")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String title;

    @Column(nullable = false)
    @NotNull
    private String description;

   @ManyToOne(optional = false)
   @JoinColumn(name = "teacher_id", nullable = false)
   @NotNull
   private User teacher;

    @Column(nullable = false)
    private Date creationDate = new Date();
}
