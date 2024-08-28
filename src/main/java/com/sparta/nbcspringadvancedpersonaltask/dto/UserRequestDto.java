package com.sparta.nbcspringadvancedpersonaltask.dto;

import com.sparta.nbcspringadvancedpersonaltask.config.PasswordEncoder;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String email;
    private String password;


}
