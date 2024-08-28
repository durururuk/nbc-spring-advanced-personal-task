package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.config.PasswordEncoder;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import com.sparta.nbcspringadvancedpersonaltask.entity.UserRoleEnum;
import com.sparta.nbcspringadvancedpersonaltask.jwt.JwtTokenProvider;
import com.sparta.nbcspringadvancedpersonaltask.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
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
    public UserResponseDto create(@RequestBody UserRequestDto requestDto, HttpServletResponse res) {
        //유저 생성 및 저장
        User user = new User(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);

        // Jwt 토큰 생성
        String token = jwtTokenProvider.createToken(user.getEmail(), UserRoleEnum.USER);

        // Jwt를 쿠키에 포함
        jwtTokenProvider.addJwtToCookie(token,res);

        return new UserResponseDto(user, "등록 성공");
    }

    /**
     * 유저 단건 조회
     *
     * @param id 조회할 유저 Id
     * @return 조회된 유저 정보 응답 Dto
     */
    @Transactional
    public UserResponseDto readById(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserResponseDto(user);
    }

    /**
     * 유저 전체 조회
     *
     * @return 유저 전체 응답 Dto 리스트
     */
    @Transactional
    public List<UserResponseDto> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::new).toList();
    }

    /**
     * 유저 삭제
     *
     * @param id 삭제할 유저 Id
     * @return 삭제된 유저 정보 응답 Dto
     */
    @Transactional
    public UserResponseDto deleteById(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return new UserResponseDto(user, "삭제 완료");
    }


}
