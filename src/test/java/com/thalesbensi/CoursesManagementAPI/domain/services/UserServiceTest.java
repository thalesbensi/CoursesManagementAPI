package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;
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
    @DisplayName("Should return a List of UserMinResponseDTO on getAllUsers method")
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

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return a UserDTO when getUserById method is called")
    void getUserByIdSuccess() {
        User userMock = mockUser();
        UserMinResponseDTO userDTOMock = mockUserMinResponseDTO();

        when(userRepository.findById(ID)).thenReturn(Optional.of(userMock));
        when(userMapper.fromUserToMinResponseDTO(userMock)).thenReturn(userDTOMock);

        UserMinResponseDTO result = userService.getUserById(ID);

        assertNotNull(result);
        assertEquals(result.name(), NAME);
        assertEquals(result.email(), EMAIL);
        assertEquals(result.userRole(), USER_ROLE);

        verify(userRepository, times(1)).findById(ID);
    }


    @Test
    @DisplayName("Should throw an Exception when getUserById method is called with a invalid or inexistent ID")
    void getUserByIdFailure() {
        when(userRepository.findById(ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.getUserById(ID));

        verify(userRepository, times(1)).findById(ID);
    }

    @Test
    @DisplayName("Should delete a User when deleteUserById method is called ")
    void deleteUserByIdSuccess() {
        when(userRepository.existsById(ID)).thenReturn(true);

        userService.deleteUserById(ID);

        verify(userRepository, times(1)).existsById(ID);
        verify(userRepository, times(1)).deleteById(ID);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("Should throw an Exception when deleteUserById method is called with a invalid or inexistent ID")
    void deleteUserByIdFailure() {
        when(userRepository.existsById(ID)).thenReturn(false);

         assertThrows(ResourceNotFoundException.class,
                () -> userService.deleteUserById(ID));

        verify(userRepository, times(1)).existsById(ID);
        verify(userRepository, never()).deleteById(ID);
        verifyNoMoreInteractions(userRepository);
    }
}