package com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto {
    private String todoTitle;
    private String todoContents;
    private Long userId;
}
