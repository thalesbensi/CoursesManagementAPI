package com.thalesbensi.CoursesManagementAPI.infrastructure.utils;

import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import org.apache.coyote.BadRequestException;

public class OwnershipVerifier {

    public static void courseOwnershipVerifier(String username, Course existingCourse) throws BadRequestException {
        if (!username.equals(existingCourse.getTeacher().getEmail())) {
            throw new BadRequestException("You are not the creator of this course");
        }
    }
}
