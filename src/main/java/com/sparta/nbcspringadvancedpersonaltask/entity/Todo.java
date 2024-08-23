package com.sparta.nbcspringadvancedpersonaltask.entity;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Todo extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    private String username;
    private String todoTitle;
    private String todoContents;

    public Todo(TodoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContents = requestDto.getTodoContents();
    }

}
