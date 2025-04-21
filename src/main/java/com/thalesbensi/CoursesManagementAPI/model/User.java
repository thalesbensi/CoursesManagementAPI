package com.thalesbensi.CoursesManagementAPI.model;

import com.thalesbensi.CoursesManagementAPI.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_tb")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = false, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(unique = false, nullable = false)
    private UserRole userRole;

}
