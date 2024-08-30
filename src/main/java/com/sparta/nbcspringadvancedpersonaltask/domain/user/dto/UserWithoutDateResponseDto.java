package com.sparta.nbcspringadvancedpersonaltask.domain.user.dto;

import com.sparta.nbcspringadvancedpersonaltask.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithoutDateResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public UserWithoutDateResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
