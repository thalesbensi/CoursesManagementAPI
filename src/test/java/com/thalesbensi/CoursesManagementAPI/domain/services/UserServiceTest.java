package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static utils.mocks.MockUser.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("UserService should return a List of UserMinResponseDTO on getAllUsers method")
    void getAllUsersSuccess() {
        //Arrange
        List<User> usersListMock = mockUserList();
        List<UserMinResponseDTO> usersDTOListsMock = mockUserMinResponseDTOList();

        when(userRepository.findAll()).thenReturn(usersListMock);
        when(userMapper.fromUserToMinResponseDTO(usersListMock.get(0))).thenReturn(usersDTOListsMock.get(0));

        //Act
        List<UserMinResponseDTO> result = userService.getAllUsers();

        //Assert
        assertNotNull(result);
        assertEquals(result.get(0).name(), NAME);
        assertEquals(result.get(0).email(), EMAIL);
        assertEquals(result.get(0).userRole(), USER_ROLE);
    }

    @Test
    @DisplayName("UserService should return a UserDTO when getUserById method is called")
    void getUserByIdSuccess() {
        User userMock = mockUser();
        UserMinResponseDTO userDTOMock = mockUserMinResponseDTO();

        when(userRepository.findById(1L)).thenReturn(Optional.of(userMock));
        when(userMapper.fromUserToMinResponseDTO(userMock)).thenReturn(userDTOMock);

        UserMinResponseDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(result.name(), NAME);
        assertEquals(result.email(), EMAIL);
        assertEquals(result.userRole(), USER_ROLE);
    }

    @Test
    @DisplayName("UserService should return a Exception when getUserById method is called with a invalid ID")
    void getUserByIdFailure() {
        User userMock = mockUser();
    }

    @Test
    void deleteUserById() {
    }
}