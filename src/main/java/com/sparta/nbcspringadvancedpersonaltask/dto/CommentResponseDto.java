package com.sparta.nbcspringadvancedpersonaltask.dto;

import com.sparta.nbcspringadvancedpersonaltask.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private final String username;
    private final String commentContents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUsername();
        this.commentContents = comment.getCommentContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
