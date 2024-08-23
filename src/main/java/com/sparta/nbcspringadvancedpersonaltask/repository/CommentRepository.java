package com.sparta.nbcspringadvancedpersonaltask.repository;

import com.sparta.nbcspringadvancedpersonaltask.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByIdAndTodoId(Long id, Long todoId);
    List<Comment> findAllByTodoId(Long todoId);
}
