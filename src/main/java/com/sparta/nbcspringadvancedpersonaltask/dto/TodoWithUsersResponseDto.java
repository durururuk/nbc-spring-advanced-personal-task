package com.sparta.nbcspringadvancedpersonaltask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoWithUsersResponseDto {
    private TodoResponseDto todoResponseDto;
    private List<UserWithoutDateResponseDto> userWithoutDateResponseDtoList;

    public TodoWithUsersResponseDto(TodoResponseDto todoResponseDto, List<UserWithoutDateResponseDto> userWithoutDateResponseDtoList) {
        this.todoResponseDto = todoResponseDto;
        this.userWithoutDateResponseDtoList = userWithoutDateResponseDtoList;
    }
}
