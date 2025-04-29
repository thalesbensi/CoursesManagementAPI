package com.thalesbensi.CoursesManagementAPI.repositories;


import com.thalesbensi.CoursesManagementAPI.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
