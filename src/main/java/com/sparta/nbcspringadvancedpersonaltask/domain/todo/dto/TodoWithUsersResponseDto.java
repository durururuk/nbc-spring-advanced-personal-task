package com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto;

import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserWithoutDateResponseDto;
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
