package com.sparta.nbcspringadvancedpersonaltask.service;

import com.sparta.nbcspringadvancedpersonaltask.dto.*;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.entity.User;
import com.sparta.nbcspringadvancedpersonaltask.entity.UserTodo;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import com.sparta.nbcspringadvancedpersonaltask.repository.UserRepository;
import com.sparta.nbcspringadvancedpersonaltask.repository.UserTodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todorepository;
    private final UserRepository userRepository;
    private final UserTodoRepository userTodoRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository, UserTodoRepository userTodoRepository) {
        this.todorepository = todoRepository;
        this.userRepository = userRepository;
        this.userTodoRepository = userTodoRepository;
    }

    //일정 생성
    @Transactional
    public TodoResponseDto create(TodoRequestDto requestDto) {
        UserTodo userTodo = new UserTodo();
        Todo todo = new Todo(requestDto);
        Todo savedTodo = todorepository.save(todo);
        User user = userRepository.findById(todo.getUserId()).orElseThrow();
        userTodo.setTodo(todo);
        userTodo.setUser(user);
        userTodoRepository.save(userTodo);
        return new TodoResponseDto(savedTodo);
    }

    //일정 담당 유저 추가
    @Transactional
    public void AddUser(Long userId, Long todoId) {
        UserTodo userTodo = new UserTodo();
        User user = userRepository.findById(userId).orElseThrow();
        Todo todo = todorepository.findById(todoId).orElseThrow();
        userTodo.setTodo(todo);
        userTodo.setUser(user);
        userTodoRepository.save(userTodo);
    }

    //일정 단건 조회
    @Transactional
    public TodoWithUsersResponseDto readById(Long id) {
        Todo todo = todorepository.findById(id).orElseThrow();
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);

        List<UserTodo> userTodoList = userTodoRepository.findByTodo_Id(id);
        List<User> userList = userTodoList.stream()
                .map(UserTodo::getUser)
                .toList();
        List<UserSimpleResponseDto> userSimpleResponseDtoList = userList.stream()
                .map(UserSimpleResponseDto::new)
                .toList();

        return new TodoWithUsersResponseDto(todoResponseDto,userSimpleResponseDtoList);
    }

    //일정 수정
    @Transactional
    public TodoResponseDto update(TodoRequestDto requestDto, Long id) {
        Todo foundTodo = todorepository.findById(id).orElseThrow();

        if(requestDto.getTodoTitle() !=null) {foundTodo.setTodoTitle(requestDto.getTodoTitle());}
        if(requestDto.getTodoContents()!=null) {foundTodo.setTodoContents(requestDto.getTodoContents());}

        Todo savedTodo = todorepository.save(foundTodo);
        return new TodoResponseDto(savedTodo);
    }

    //일정 전체 조회
    @Transactional
    public Page<TodoResponseDto> readAll(Pageable pageable) {
        int page = (pageable != null && pageable.getPageNumber() >= 0) ? pageable.getPageNumber() : 0;
        int size = (pageable != null && pageable.getPageSize() > 0) ? pageable.getPageSize() : 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");

        Pageable sortedPageable = PageRequest.of(page,size,sort);
        return todorepository.findAll(sortedPageable).map(TodoResponseDto::new);
    }

    //일정 삭제
    @Transactional
    public TodoResponseDto delete(Long id) {
        Todo todo = todorepository.findById(id).orElseThrow();
        todorepository.delete(todo);
        return new TodoResponseDto(todo);
    }
}
