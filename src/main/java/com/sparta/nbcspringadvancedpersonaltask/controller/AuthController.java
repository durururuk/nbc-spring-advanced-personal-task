package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.LoginRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.jwt.JwtTokenProvider;
import com.sparta.nbcspringadvancedpersonaltask.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    JwtTokenProvider jwtTokenProvider;
    public AuthController (JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtTokenProvider.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtTokenProvider.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        //토큰에서 사용자 정보 가져오기
        Claims info = jwtTokenProvider.getUserInfoFromToken(token);
        //사용자 email
        String email = info.getSubject();
        System.out.println("email = " + email);
        //사용자 권한
        String authority = (String) info.get(jwtTokenProvider.AUTHORIZATION_KEY);
        System.out.println("authority = " + authority);

        return "getJwt :" + email + ", " + authority;
    }

    @PostMapping("/regi")
    public UserResponseDto create(@RequestBody UserRequestDto requestDto, HttpServletResponse res) {
        return userService.create(requestDto, res);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        return userService.login(requestDto,res);
    }
}
