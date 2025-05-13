package com.thalesbensi.CoursesManagementAPI.infrastructure.utils;

import com.thalesbensi.CoursesManagementAPI.domain.entity.Comment;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.UnauthorizedException;

public class OwnershipVerifier {

    public static void courseOwnershipVerifier(String username, Course existingCourse){
        if (!username.equals(existingCourse.getTeacher().getEmail())) {
            throw new UnauthorizedException("You are not the creator of this course");
        }
    }

    public static void commentOwnershipVerifier(Comment comment, String userEmail) {
        if (!comment.getStudent().getEmail().equals(userEmail)) {
            throw new UnauthorizedException("You are not allowed to edit this comment.");
        }
    }

}
