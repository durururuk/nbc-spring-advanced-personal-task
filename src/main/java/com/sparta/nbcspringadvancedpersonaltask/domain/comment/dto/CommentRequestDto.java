package com.sparta.nbcspringadvancedpersonaltask.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String username;
    private String commentContents;
}
