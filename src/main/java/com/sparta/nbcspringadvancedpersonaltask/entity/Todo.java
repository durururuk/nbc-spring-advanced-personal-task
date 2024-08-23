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

    private String username;
    private String todoTitle;
    private String todoContents;

    public Todo(TodoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContents = requestDto.getTodoContents();
    }

    @OneToMany(mappedBy = "todo")
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
        if(comment.getTodo() != this) {
            comment.setTodo(this);
        }
    }

}
