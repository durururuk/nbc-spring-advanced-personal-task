package com.sparta.nbcspringadvancedpersonaltask.entity;

import com.sparta.nbcspringadvancedpersonaltask.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    private String username;
    private String commentContents;

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.commentContents = requestDto.getCommentContents();
    }

    @ManyToOne
    @JoinColumn(name = "TODO_ID")
    private Todo todo;

    public void setTodo(Todo todo) {
        this.todo = todo;
        if(!todo.getComments().contains(this)) {
            todo.getComments().add(this);
        }
    }
}
