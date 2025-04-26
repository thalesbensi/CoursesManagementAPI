package com.thalesbensi.CoursesManagementAPI.services;

import com.thalesbensi.CoursesManagementAPI.dto.UserDTO;
import com.thalesbensi.CoursesManagementAPI.exceptions.ResourceNotFoundException;
import com.thalesbensi.CoursesManagementAPI.model.User;
import com.thalesbensi.CoursesManagementAPI.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO:: fromEntity).toList();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
        return UserDTO.fromEntity(user);
    }

    public UserDTO createUser(@Valid User user) {
        userRepository.save(user);
        return UserDTO.fromEntity(user);

    }

    public UserDTO updateUser(@Valid Long id, User user) {
       User userToBeUpdated = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
       userToBeUpdated.setName(user.getName());
       userToBeUpdated.setEmail(user.getEmail());
       userToBeUpdated.setPassword(user.getPassword());
       userRepository.save(userToBeUpdated);
       return UserDTO.fromEntity(userToBeUpdated);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found! :(");
        }
        userRepository.deleteById(id);
    }
}
