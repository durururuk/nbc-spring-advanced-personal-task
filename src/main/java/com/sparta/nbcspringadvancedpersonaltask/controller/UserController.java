package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public UserResponseDto readById(Long id) {
        return userService.readById(id);
    }

    @GetMapping
    public List<UserResponseDto> readAll() {
        return userService.readAll();
    }

    @DeleteMapping
    public ResponseEntity<UserResponseDto> deleteById(Long id) {
        return userService.deleteById(id);
    }


}
