package com.thalesbensi.CoursesManagementAPI.domain.repositories;

import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { 

    UserDetails findByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
