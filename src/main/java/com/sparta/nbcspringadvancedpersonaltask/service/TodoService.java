package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todorepository;
    public TodoService(TodoRepository todoRepository) {
        this.todorepository = todoRepository;
    }

    //일정 생성
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        Todo savedTodo = todorepository.save(todo);
        return new TodoResponseDto(savedTodo);
    }

    //일정 단건 조회
    public TodoResponseDto readTodoById(Long id) {
        Todo FoundTodo = todorepository.findById(id).orElseThrow();
        return new TodoResponseDto(FoundTodo);
    }
}
