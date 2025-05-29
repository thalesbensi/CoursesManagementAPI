package com.thalesbensi.CoursesManagementAPI.api.controller;

import com.thalesbensi.CoursesManagementAPI.api.dto.response.user.UserMinResponseDTO;
import com.thalesbensi.CoursesManagementAPI.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping()
    public ResponseEntity<List<UserMinResponseDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserMinResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserMinResponseDTO> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
