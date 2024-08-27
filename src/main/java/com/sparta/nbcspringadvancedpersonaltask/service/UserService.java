package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.UserRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.UserResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import com.sparta.nbcspringadvancedpersonaltask.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto create(@RequestBody UserRequestDto requestDto) {
        User user = new User(requestDto);
        userRepository.save(user);
        return new UserResponseDto(user, "등록 성공");
    }

    public UserResponseDto readById(@RequestParam Long id) {
         User user = userRepository.findById(id).orElseThrow();
         return new UserResponseDto(user);
    }

    public List<UserResponseDto> readAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto deleteById(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return new UserResponseDto(user, "삭제 완료");
    }
}
