package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //일정 등록
    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    //일정 단건 조회
    @GetMapping("/readById")
    public TodoResponseDto readTodoById(@RequestParam Long id) {
        return todoService.readTodoById(id);
    }

    //일정 전체 조회(페이징)
    @GetMapping
    public Page<TodoResponseDto> readAllTodo(@RequestParam(required = false) Pageable pageable) {
        return todoService.readAllTodo(pageable);
    };

    //일정 수정
    @PutMapping
    public TodoResponseDto updateTodo(@RequestParam Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return todoService.updateTodoViaId(todoRequestDto, id);
    }


}
