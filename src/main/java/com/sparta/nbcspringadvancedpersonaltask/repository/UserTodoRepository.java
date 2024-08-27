package com.sparta.nbcspringadvancedpersonaltask.repository;

import com.sparta.nbcspringadvancedpersonaltask.entity.UserTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTodoRepository extends JpaRepository<UserTodo, Long> {
}
