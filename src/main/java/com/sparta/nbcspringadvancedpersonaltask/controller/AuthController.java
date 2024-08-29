package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.LoginRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.jwt.JwtTokenProvider;
import com.sparta.nbcspringadvancedpersonaltask.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    JwtTokenProvider jwtTokenProvider;
    public AuthController (JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     *
     * @param requestDto
     * @param res
     * @return
     */
    @PostMapping("/regi")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto requestDto, HttpServletResponse res) {
        return userService.create(requestDto, res);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto,res);
    }
}
