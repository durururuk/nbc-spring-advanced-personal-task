package com.sparta.nbcspringadvancedpersonaltask.domain.manager.repository;

import com.sparta.nbcspringadvancedpersonaltask.domain.manager.entity.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTodoRepository extends JpaRepository<UserTodo, Long> {
    List<UserTodo> findByTodo_Id(Long todoId);
}
