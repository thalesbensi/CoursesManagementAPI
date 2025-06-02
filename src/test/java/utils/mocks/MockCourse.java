package utils.mocks;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.course.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.course.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.enums.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockCourse {

    public static Long ID = 1L;
    public static String TITLE = "Java With Spring";
    public static String DESCRIPTION = "How to use Spring and develop a RESTful API";
    public static User TEACHER = MockUser.mockUser();
    public static Date CREATION_DATE = new Date();

    public static Course mockCourse() {
        return new Course(ID,TITLE,DESCRIPTION,TEACHER,CREATION_DATE);
    }

    public static CourseResponseDTO mockCourseResponseDTO() {
        return new CourseResponseDTO(ID,TITLE,DESCRIPTION, TEACHER.getName(), CREATION_DATE);
    }

    public static CourseRequestDTO mockCourseRequestDTO() {
        return new CourseRequestDTO(TITLE, DESCRIPTION);
    }

    public static List<Course> mockCourseList() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(mockCourse());
        return courseList;
    }

    public static List<CourseResponseDTO> mockCourseDTOList() {
        CourseResponseDTO courseDTO = mockCourseResponseDTO();
        List<CourseResponseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(courseDTO);
        return courseDTOList;
    }



}
