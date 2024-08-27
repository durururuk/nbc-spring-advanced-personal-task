package com.sparta.nbcspringadvancedpersonaltask.entity;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Todo extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    private Long userId;
    private String todoTitle;
    private String todoContents;

    public Todo(TodoRequestDto requestDto) {
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContents = requestDto.getTodoContents();
        this.userId = requestDto.getUserId();
    }

    @OneToMany(mappedBy = "todo")
    private List<UserTodo> UserTodoList = new ArrayList<>();

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if(comment.getTodo() != this) {
            comment.setTodo(this);
        }
    }

}
