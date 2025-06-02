package com.thalesbensi.CoursesManagementAPI.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "course_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String title;

    @Column
    @NotNull
    private String description;

   @ManyToOne(optional = false)
   @JoinColumn(name = "teacher_id")
   @NotNull
   private User teacher;

    @Column(name = "created_at")
    private Date creationDate = new Date();
}
