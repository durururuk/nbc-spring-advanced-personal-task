package com.sparta.nbcspringadvancedpersonaltask.domain.auth.controller;

import com.sparta.nbcspringadvancedpersonaltask.domain.auth.dto.LoginRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.jwt.JwtTokenProvider;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.service.UserService;
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
     * 유저 등록
     * @param requestDto 유저 등록 정보
     * @param res 응답 인터페이스
     * @return 등록된 유저 정보
     */
    @PostMapping("/regi")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto requestDto, HttpServletResponse res) {
        return userService.create(requestDto, res);
    }

    /**
     *
     * @param requestDto 로그인 정보 (이메일, 비밀번호)
     * @param res 응답 인터페이스
     * @return JWT 토큰
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto,res);
    }
}
