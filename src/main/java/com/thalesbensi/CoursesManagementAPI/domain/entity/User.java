package com.thalesbensi.CoursesManagementAPI.domain.entity;

import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;
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
    @Column(nullable = false)
    @NotNull
    private UserRole userRole;
}
