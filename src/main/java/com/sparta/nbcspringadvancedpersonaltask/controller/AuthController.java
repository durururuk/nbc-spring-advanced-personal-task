package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.entity.UserRoleEnum;
import com.sparta.nbcspringadvancedpersonaltask.jwt.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    JwtTokenProvider jwtTokenProvider;
    public AuthController (JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res) {
        //Jwt 생성
        String token  = jwtTokenProvider.createToken("abcd1234@gmail.com", UserRoleEnum.USER);

        //Jwt 쿠키 저장
        jwtTokenProvider.addJwtToCookie(token,res);
        return "createJwt : " + token;
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
}
