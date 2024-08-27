package com.sparta.nbcspringadvancedpersonaltask.dto;

import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;
    private String message = "조회 성공";

    public UserResponseDto(User user, String message) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
        this.message = message;
    }

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
