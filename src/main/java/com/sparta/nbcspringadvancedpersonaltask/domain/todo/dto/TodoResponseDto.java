package com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto;

import com.sparta.nbcspringadvancedpersonaltask.domain.todo.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private final Long id;
    private final String todoTitle;
    private final String todoContents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String weather;

    public TodoResponseDto(Todo todo) {
        this.todoTitle = todo.getTodoTitle();
        this.todoContents = todo.getTodoContents();
        this.createdAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
        this.id = todo.getId();
        this.weather = todo.getWeather();
    }
}
