package com.sparta.nbcspringadvancedpersonaltask.domain.comment.dto;

import com.sparta.nbcspringadvancedpersonaltask.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private final String username;
    private final String commentContents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private String message;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public CommentResponseDto(Comment comment , String message) {
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.message = message;
    }
}
