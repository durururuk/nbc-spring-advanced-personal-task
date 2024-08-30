package com.sparta.nbcspringadvancedpersonaltask.domain.comment.service;

import com.sparta.nbcspringadvancedpersonaltask.domain.comment.dto.CommentRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.comment.dto.CommentResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.comment.entity.Comment;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.domain.comment.repository.CommentRepository;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentService(CommentRepository commentRepository, TodoRepository todoRepository) {
        this.commentRepository = commentRepository;
        this.todoRepository = todoRepository;
    }

    /**
     * 댓글 작성
     *
     * @param requestDto 댓글 요청 Dto
     * @param id         댓글이 달릴 일정 Id
     * @return 작성된 댓글 응답 Dto
     */
    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(CommentRequestDto requestDto, Long id) {
        Comment comment = new Comment(requestDto);
        Todo Foundtodo = todoRepository.findById(id).orElseThrow();
        Foundtodo.addComment(comment);
        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity.ok(new CommentResponseDto(savedComment, "등록 완료"));
    }

    /**
     * 댓글 단건 조회
     *
     * @param commentId 조회할 댓글 Id
     * @param todoId    조회할 댓글이 있는 원 일정 Id
     * @return 조회된 댓글 응답 Dto
     */
    @Transactional
    public ResponseEntity<CommentResponseDto> readCommentByIdAndTodoId(Long commentId, Long todoId) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId, todoId);
        return ResponseEntity.ok(new CommentResponseDto(foundComment, "조회 성공"));
    }

    /**
     * 댓글 전체 조회
     *
     * @param todoId 댓글을 조회할 원 일정 Id
     * @return 댓글 리스트 응답 Dto
     */
    @Transactional
    public ResponseEntity<List<CommentResponseDto>> readAllCommentByTodoId(Long todoId) {
        List<Comment> comments = commentRepository.findAllByTodoId(todoId);
        return ResponseEntity.ok(comments.stream().map(CommentResponseDto::new).toList());
    }

    /**
     * 댓글 수정 : 작성자명, 수정할 내용이 모두 들어있지 않아도 동작하게
     *
     * @param commentId  수정할 댓글 Id
     * @param todoId     수정할 댓글이 있는 원 일정 Id
     * @param requestDto 수정할 내용 요청 Dto
     * @return 수정된 내용 응답 Dto
     */
    @Transactional
    public ResponseEntity<CommentResponseDto> updateCommentByIdAndTodoId(Long commentId, Long todoId, CommentRequestDto requestDto) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId, todoId);

        if (requestDto.getUsername() != null) {
            foundComment.setUsername(requestDto.getUsername());
        }
        if (requestDto.getCommentContents() != null) {
            foundComment.setCommentContents(requestDto.getCommentContents());
        }

        Comment updatedComment = commentRepository.save(foundComment);
        return ResponseEntity.ok(new CommentResponseDto(updatedComment, "수정 완료"));
    }

    /**
     * 댓글 삭제
     *
     * @param commentId 삭제할 댓글 Id
     * @param todoId    : 삭제할 댓글이 있는 원 일정 Id
     * @return : 삭제된 내용 응답 Dto
     */
    @Transactional
    public ResponseEntity<CommentResponseDto> deleteCommentByIdAndTodoId(Long commentId, Long todoId) {
        Comment foundComment = commentRepository.findByIdAndTodoId(commentId, todoId);
        commentRepository.delete(foundComment);
        return ResponseEntity.ok(new CommentResponseDto(foundComment, "삭제 완료"));
    }
}
