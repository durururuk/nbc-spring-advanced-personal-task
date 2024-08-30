package com.sparta.nbcspringadvancedpersonaltask.domain.user.dto;

import com.sparta.nbcspringadvancedpersonaltask.domain.user.enums.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private UserRoleEnum role;
}
