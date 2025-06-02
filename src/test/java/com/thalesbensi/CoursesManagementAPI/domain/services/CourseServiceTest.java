package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.course.CourseRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.course.CourseResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.Course;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.CourseRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.LessonRepository;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.CourseMapper;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.LessonMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import utils.mocks.MockCourse;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static utils.mocks.MockCourse.*;
import static utils.mocks.MockUser.*;
import static utils.mocks.MockUser.ID;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private CourseService courseService;

    @Test
    @DisplayName("Should return a List of CourseResponseDTO on getAllCourses method")
    void getAllCourses() {
        List<Course> courseList = MockCourse.mockCourseList();
        List<CourseResponseDTO> courseResponseDTOList = MockCourse.mockCourseDTOList();

        when(courseRepository.findAll()).thenReturn(courseList);
        when(courseMapper.toResponseDTO(courseList.get(0))).thenReturn(courseResponseDTOList.get(0));

        List<CourseResponseDTO> result = courseService.getAllCourses();

        assertNotNull(result);
        assertEquals(result.get(0).title(), TITLE);
        assertEquals(result.get(0).description(), DESCRIPTION);
        assertEquals(result.get(0).teacherName(), TEACHER.getName());
        assertEquals(result.get(0).creationDate(), CREATION_DATE);

        verify(courseRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a CourseResponseDTO when getCourseById method is called")
    void getCourseByIdSuccess() {
        Course courseMock = MockCourse.mockCourse();
        CourseResponseDTO courseResponseDTOMock = MockCourse.mockCourseResponseDTO();

        when(courseRepository.findById(ID)).thenReturn(Optional.of(courseMock));
        when(courseMapper.toResponseDTO(courseMock)).thenReturn(courseResponseDTOMock);

        CourseResponseDTO result = courseService.getCourseById(ID);

        assertNotNull(result);
        assertEquals(courseResponseDTOMock.title(), TITLE);
        assertEquals(courseResponseDTOMock.description(), DESCRIPTION);
        assertEquals(courseResponseDTOMock.teacherName(), TEACHER.getName());
        assertEquals(courseResponseDTOMock.creationDate(), CREATION_DATE);

        verify(courseRepository, times(1)).findById(ID);
    }

    @Test
    @DisplayName("Should throw an Exception when getCourseById method is called with a invalid or inexistent ID")
    void getCourseByIdFailure() {
        when(courseRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> courseService.getCourseById(ID));

        verify(courseRepository, times(1)).findById(ID);
    }

    @Test
    void getCourseLessons() {
    }

    @Test
    @DisplayName("Should create a Course when createCourse method is called")
    void createCourseSuccess() {
        Authentication auth = new UsernamePasswordAuthenticationToken(EMAIL, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
        CourseRequestDTO requestDTO = mockCourseRequestDTO();
        Course course = mockCourse();
        CourseResponseDTO mockCourseResponseDTO = mockCourseResponseDTO();

        when(userRepository.findUserByEmail(EMAIL)).thenReturn(Optional.of(mockUser()));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(courseMapper.toResponseDTO(course)).thenReturn(mockCourseResponseDTO);

        CourseResponseDTO result = courseService.createCourse(requestDTO);

        assertNotNull(result);
        assertEquals(mockCourseResponseDTO.title(), TITLE);
        assertEquals(mockCourseResponseDTO.description(), DESCRIPTION);

        verify(userRepository, times(1)).findUserByEmail(EMAIL);
        verify(courseRepository, times(1)).save(any(Course.class));

        SecurityContextHolder.clearContext();

    }

    @Test
    @DisplayName("Should delete a Course when deleteCourseById method is called")
    void deleteCourseByIdSuccess() {
        when(courseRepository.existsById(ID)).thenReturn(true);

        courseService.deleteCourseById(ID);

        verify(courseRepository, times(1)).existsById(ID);
        verify(courseRepository, times(1)).deleteById(ID);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Should throw an Exception when deleteCourseById method is called with a invalid or inexistent ID")
    void deleteCourseByIdFailure() {
        when(courseRepository.existsById(ID)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> courseService.deleteCourseById(ID));

        verify(courseRepository, times(1)).existsById(ID);
        verify(courseRepository, never()).deleteById(ID);
        verifyNoMoreInteractions(userRepository);
    }
}