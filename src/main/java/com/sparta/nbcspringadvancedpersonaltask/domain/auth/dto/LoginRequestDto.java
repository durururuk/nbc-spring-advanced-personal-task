package com.sparta.nbcspringadvancedpersonaltask.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
