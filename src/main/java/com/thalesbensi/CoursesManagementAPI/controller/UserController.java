package com.thalesbensi.CoursesManagementAPI.controller;

import com.thalesbensi.CoursesManagementAPI.dto.UserDTO;
import com.thalesbensi.CoursesManagementAPI.model.User;
import com.thalesbensi.CoursesManagementAPI.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
