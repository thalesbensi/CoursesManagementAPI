package com.thalesbensi.CoursesManagementAPI.model;

import com.thalesbensi.CoursesManagementAPI.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user_tb")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(unique = false, nullable = false)
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(unique = false, nullable = false)
    @NotNull
    private UserRole userRole = UserRole.STUDENT;
}
