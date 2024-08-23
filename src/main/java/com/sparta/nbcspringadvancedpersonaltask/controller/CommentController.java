package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.CommentRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.CommentResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 등록
    @PostMapping
    public CommentResponseDto createComment(@RequestParam Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto,id);
    }

    @GetMapping("/readById")
    //댓글 단건 조회
    public CommentResponseDto readCommentByCommentIdAndTodoId(@RequestParam Long commentId, @RequestParam Long todoId) {
        return commentService.readCommentByIdAndTodoId(commentId, todoId);
    }

    //댓글 전체 조회
    @GetMapping
    public List<CommentResponseDto> readAllCommentByTodoId(@RequestParam Long todoId) {
        return commentService.readAllCommentByTodoId(todoId);
    }

    //댓글 수정
    @PutMapping
    public CommentResponseDto updateCommentByIdAndTodoId(@RequestParam Long commentId, @RequestParam Long todoId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateCommentByIdAndTodoId(commentId,todoId,requestDto);
    }


}
