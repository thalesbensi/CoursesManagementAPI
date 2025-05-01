package com.thalesbensi.CoursesManagementAPI.domain.repositories;

import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
