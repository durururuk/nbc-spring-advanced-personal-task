package com.sparta.nbcspringadvancedpersonaltask.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String username;
    private String commentContents;
}
