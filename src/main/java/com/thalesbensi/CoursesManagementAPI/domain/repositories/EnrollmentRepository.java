package com.thalesbensi.CoursesManagementAPI.domain.repositories;


import com.thalesbensi.CoursesManagementAPI.domain.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> { }
