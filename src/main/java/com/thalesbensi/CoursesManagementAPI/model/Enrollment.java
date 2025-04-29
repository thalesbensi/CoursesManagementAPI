package com.thalesbensi.CoursesManagementAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "enrollment_tb")
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Date enrollmentDate = new Date();
}
