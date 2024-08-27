package com.sparta.nbcspringadvancedpersonaltask.dto;

import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSimpleResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public UserSimpleResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
