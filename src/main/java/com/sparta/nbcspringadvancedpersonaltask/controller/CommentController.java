package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.CommentRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.CommentResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponseDto createComment(@RequestParam Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto,id);
    }
}
