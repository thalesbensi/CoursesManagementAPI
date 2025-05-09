package com.thalesbensi.CoursesManagementAPI.domain.repositories;

import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> { 

    UserDetails findByEmail(String email);
}
