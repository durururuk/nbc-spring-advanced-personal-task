package com.sparta.nbcspringadvancedpersonaltask.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoWithUsersResponseDto {
    private TodoResponseDto todoResponseDto;
    private List<UserSimpleResponseDto> userSimpleResponseDtoList;

    public TodoWithUsersResponseDto(TodoResponseDto todoResponseDto, List<UserSimpleResponseDto> userSimpleResponseDtoList) {
        this.todoResponseDto = todoResponseDto;
        this.userSimpleResponseDtoList = userSimpleResponseDtoList;
    }
}
