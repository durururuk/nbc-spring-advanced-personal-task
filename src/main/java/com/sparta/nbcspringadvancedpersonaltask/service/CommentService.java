package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.CommentRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.CommentResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Comment;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.repository.CommentRepository;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    //댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long id) {
        Comment comment = new Comment(requestDto);
        Todo Foundtodo = todoRepository.findById(id).orElseThrow();
        Foundtodo.addComment(comment);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    //댓글 단건 조회
    public CommentResponseDto readCommentByIdAndTodoId(Long commentId, Long TodoId) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId,TodoId);
        return new CommentResponseDto(foundComment);
    }

}
