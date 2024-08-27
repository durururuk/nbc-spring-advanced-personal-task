package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.CommentRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.CommentResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Comment;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.repository.CommentRepository;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    //댓글 작성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long id) {
        Comment comment = new Comment(requestDto);
        Todo Foundtodo = todoRepository.findById(id).orElseThrow();
        Foundtodo.addComment(comment);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment, "등록 완료");
    }

    //댓글 단건 조회
    @Transactional
    public CommentResponseDto readCommentByIdAndTodoId(Long commentId, Long todoId) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId,todoId);
        return new CommentResponseDto(foundComment, "조회 성공");
    }

    //댓글 전체 조회
    @Transactional
    public List<CommentResponseDto> readAllCommentByTodoId(Long todoId) {
        List<CommentResponseDto> responseDtos = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByTodoId(todoId);
        for(Comment comment : comments) {
            responseDtos.add(new CommentResponseDto(comment, "조회 성공"));
        }
        return responseDtos;
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateCommentByIdAndTodoId(Long commentId, Long todoId, CommentRequestDto requestDto) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId,todoId);

        if(requestDto.getUsername() != null) {foundComment.setUsername(requestDto.getUsername());}
        if(requestDto.getCommentContents()!=null) {foundComment.setCommentContents(requestDto.getCommentContents());}

        Comment updatedComment = commentRepository.save(foundComment);
        return new CommentResponseDto(updatedComment , "수정 완료");
    }

    //댓글 삭제
    @Transactional
    public CommentResponseDto deleteCommentByIdAndTodoId(Long commentId, Long todoId) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId,todoId);
        commentRepository.delete(foundComment);
        return new CommentResponseDto(foundComment , "삭제 완료");
    }

}
