package com.sparta.nbcspringadvancedpersonaltask.controller;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoWithUsersResponseDto;
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
    public TodoResponseDto create(@RequestBody TodoRequestDto requestDto) {
        return todoService.create(requestDto);
    }

    //일정 담당자 추가
    @PutMapping("/add")
    public void addUser(@RequestParam Long todoId, @RequestParam Long userId) {
        todoService.AddUser(userId, todoId);
    }

    //일정 단건 조회
    @GetMapping("/id")
    public TodoWithUsersResponseDto readByIdt(@RequestParam Long id) {
        return todoService.readById(id);
    }

    //일정 전체 조회(페이징)
    @GetMapping
    public Page<TodoResponseDto> readAll(@RequestParam(required = false) Pageable pageable) {
        return todoService.readAll(pageable);
    }

    //일정 수정
    @PutMapping
    public TodoResponseDto update(@RequestParam Long id, @RequestBody TodoRequestDto todoRequestDto) {
        return todoService.update(todoRequestDto, id);
    }

    //일정 삭제
    @DeleteMapping
    public TodoResponseDto delete(@RequestParam Long id) {
        return todoService.delete(id);
    }


}
