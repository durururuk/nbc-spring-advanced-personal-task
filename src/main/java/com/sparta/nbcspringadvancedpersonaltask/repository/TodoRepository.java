package com.sparta.nbcspringadvancedpersonaltask.repository;

import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
