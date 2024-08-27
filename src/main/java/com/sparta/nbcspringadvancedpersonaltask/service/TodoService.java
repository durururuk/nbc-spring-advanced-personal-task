package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {
    private final TodoRepository todorepository;
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

    //일정 수정
    @Transactional
    public TodoResponseDto updateTodoViaId(TodoRequestDto requestDto, Long id) {
        Todo foundTodo = todorepository.findById(id).orElseThrow();

        if(requestDto.getTodoTitle() !=null) {foundTodo.setTodoTitle(requestDto.getTodoTitle());}
        if(requestDto.getTodoContents()!=null) {foundTodo.setTodoContents(requestDto.getTodoContents());}

        Todo savedTodo = todorepository.save(foundTodo);
        return new TodoResponseDto(savedTodo);
    }

    //일정 전체 조회
    public Page<TodoResponseDto> readAllTodo(Pageable pageable) {
        int page = (pageable != null && pageable.getPageNumber() >= 0) ? pageable.getPageNumber() : 0;
        int size = (pageable != null && pageable.getPageSize() > 0) ? pageable.getPageSize() : 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");

        Pageable sortedPageable = PageRequest.of(page,size,sort);
        return todorepository.findAll(sortedPageable).map(TodoResponseDto::new);
    }
}
