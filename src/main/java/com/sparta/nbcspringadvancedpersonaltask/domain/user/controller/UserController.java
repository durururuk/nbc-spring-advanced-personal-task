package com.sparta.nbcspringadvancedpersonaltask.domain.user.controller;

import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id")
    public ResponseEntity<UserResponseDto> readById(Long id) {
        return userService.readById(id);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> readAll() {
        return userService.readAll();
    }

    @DeleteMapping
    public ResponseEntity<UserResponseDto> deleteById(Long id) {
        return userService.deleteById(id);
    }


}
