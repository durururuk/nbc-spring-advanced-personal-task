package com.sparta.nbcspringadvancedpersonaltask.domain.todo.controller;

import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoWithUsersResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TodoResponseDto> create(@RequestBody TodoRequestDto requestDto) {
        return todoService.create(requestDto);
    }

    //일정 담당자 추가
    @PutMapping("/add")
    public void addUser(@RequestParam Long todoId, @RequestParam Long userId) {
        todoService.AddUser(userId, todoId);
    }

    //일정 단건 조회
    @GetMapping("/id")
    public ResponseEntity<TodoWithUsersResponseDto> readByIdt(@RequestParam Long id) {
        return todoService.readById(id);
    }

    //일정 전체 조회(페이징)
    @GetMapping
    public ResponseEntity<Page<TodoResponseDto>> readAll(@RequestParam(required = false) Pageable pageable) {
        return todoService.readAll(pageable);
    }

    //일정 수정
    @PutMapping("/admin")
    public ResponseEntity<TodoResponseDto> update(@RequestParam Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return todoService.update(todoRequestDto, id);
    }

    //일정 삭제
    @DeleteMapping("/admin")
    public ResponseEntity<TodoResponseDto> delete(@RequestParam Long id) {
        return todoService.delete(id);
    }


}
