package com.sparta.nbcspringadvancedpersonaltask.dto;

import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private final String todoTitle;
    private final String todoContents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.todoTitle = todo.getTodoTitle();
        this.todoContents = todo.getTodoContents();
        this.createdAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
    }
}
