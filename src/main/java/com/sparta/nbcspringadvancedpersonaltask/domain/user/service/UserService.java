package com.sparta.nbcspringadvancedpersonaltask.domain.user.service;

import com.sparta.nbcspringadvancedpersonaltask.config.PasswordEncoder;
import com.sparta.nbcspringadvancedpersonaltask.domain.auth.dto.LoginRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.entity.User;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.enums.UserRoleEnum;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.jwt.JwtTokenProvider;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, @Qualifier("resourceHandlerMapping") HandlerMapping resourceHandlerMapping) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    /**
     * 유저 등록
     *
     * @param requestDto 유저 정보 요청 Dto
     * @return 유저 정보 응답 Dto
     */
    @Transactional
    public ResponseEntity<UserResponseDto> create(UserRequestDto requestDto, HttpServletResponse res) {
        //유저 생성 및 저장
        User user = new User(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        // Jwt 토큰 생성
        if (user.getRole().getAuthority().equals(UserRoleEnum.Authority.ADMIN)) {
            String token = jwtTokenProvider.createToken(user.getEmail(), UserRoleEnum.ADMIN);
        }
        String token = jwtTokenProvider.createToken(user.getEmail(), UserRoleEnum.USER);


        // Jwt를 헤더에 포함
        jwtTokenProvider.addJwtToHeader(token, res);

        return ResponseEntity.ok(new UserResponseDto(user, "등록 성공"));
    }

    /**
     * 유저 단건 조회
     *
     * @param id 조회할 유저 Id
     * @return 조회된 유저 정보 응답 Dto
     */
    @Transactional
    public ResponseEntity<UserResponseDto> readById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new UserResponseDto(user));
    }

    /**
     * 유저 전체 조회
     *
     * @return 유저 전체 응답 Dto 리스트
     */
    @Transactional
    public ResponseEntity<List<UserResponseDto>> readAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream().map(UserResponseDto::new).toList());
    }

    /**
     * 유저 삭제
     *
     * @param id 삭제할 유저 Id
     * @return 삭제된 유저 정보 응답 Dto
     */
    @Transactional
    public ResponseEntity<UserResponseDto> deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return ResponseEntity.ok(new UserResponseDto(user, "삭제 완료"));
    }

    /**
     * 로그인
     *
     * @param requestDto 로그인 정보 담은 Dto
     * @param res        응답 인터페이스
     * @return 토큰 반환
     */
    @Transactional
    public ResponseEntity<String> login(LoginRequestDto requestDto, HttpServletResponse res) {
        User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow();
        boolean pwdMatch = passwordEncoder.matches(requestDto.getPassword(), user.getPassword());

        if (pwdMatch) {
            String token = jwtTokenProvider.createToken(requestDto.getEmail(), user.getRole());
            jwtTokenProvider.addJwtToHeader(token, res);
            return ResponseEntity.ok("로그인 성공. JWT 토큰 : " + token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
    }
}
