package com.thalesbensi.CoursesManagementAPI.domain.services;

import com.thalesbensi.CoursesManagementAPI.api.dto.request.UserRequestDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.api.dto.response.UserResponseDTO;
import com.thalesbensi.CoursesManagementAPI.infrastructure.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.infrastructure.mapper.UserMapper;
import com.thalesbensi.CoursesManagementAPI.domain.entity.User;
import com.thalesbensi.CoursesManagementAPI.domain.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserMinResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::fromUserToMinResponseDTO).toList();
    }

    public UserMinResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return userMapper.fromUserToMinResponseDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO user) {
        User userToBeCreated = userMapper.toEntity(user);
        userRepository.save(userToBeCreated);
        return userMapper.toResponseDTO(userToBeCreated);

    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO user) {
       User userToBeUpdated = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
       userToBeUpdated.setName(user.name());
       userToBeUpdated.setEmail(user.email());
       userToBeUpdated.setPassword(user.password());
       userRepository.save(userToBeUpdated);
       return userMapper.toResponseDTO(userToBeUpdated);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found! :(");
        }
        userRepository.deleteById(id);
    }
}
