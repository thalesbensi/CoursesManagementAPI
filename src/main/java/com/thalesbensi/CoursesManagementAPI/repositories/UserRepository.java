package com.thalesbensi.CoursesManagementAPI.repositories;

import com.thalesbensi.CoursesManagementAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
