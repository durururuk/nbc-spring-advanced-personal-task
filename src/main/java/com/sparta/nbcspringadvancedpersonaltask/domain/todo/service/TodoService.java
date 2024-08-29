package com.sparta.nbcspringadvancedpersonaltask.domain.todo.service;

import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.dto.TodoWithUsersResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.dto.UserWithoutDateResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.entity.User;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.entity.UserTodo;
import com.sparta.nbcspringadvancedpersonaltask.domain.todo.repository.TodoRepository;
import com.sparta.nbcspringadvancedpersonaltask.domain.user.repository.UserRepository;
import com.sparta.nbcspringadvancedpersonaltask.domain.manager.repository.UserTodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final UserTodoRepository userTodoRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository, UserTodoRepository userTodoRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.userTodoRepository = userTodoRepository;
    }

    /**
     * 일정 생성
     *
     * @param requestDto 일정 내용 요청 Dto
     * @return 일정 내용 응답 Dto
     */
    @Transactional
    public ResponseEntity<TodoResponseDto> create(TodoRequestDto requestDto) {
        //일정 생성
        UserTodo userTodo = new UserTodo();
        Todo todo = new Todo(requestDto);
        Todo savedTodo = todoRepository.save(todo);

        //조인테이블에 등록
        User user = userRepository.findById(todo.getUserId()).orElseThrow();
        userTodo.setTodo(todo);
        userTodo.setUser(user);
        userTodoRepository.save(userTodo);

        return ResponseEntity.ok(new TodoResponseDto(savedTodo));
    }

    /**
     * 작성자 이외 일정 담당할 유저 추가
     *
     * @param userId : 일정 담당 유저 Id
     * @param todoId : 담당할 일정 Id
     */
    @Transactional
    public void AddUser(Long userId, Long todoId) {
        UserTodo userTodo = new UserTodo();
        User user = userRepository.findById(userId).orElseThrow();
        Todo todo = todoRepository.findById(todoId).orElseThrow();
        userTodo.setTodo(todo);
        userTodo.setUser(user);
        userTodoRepository.save(userTodo);
    }

    /**
     * 일정 단건 조회
     *
     * @param id : 조회할 일정 Id
     * @return : 해당 일정 응답 Dto , 담당할 유저들 정보 응답 Dto 리스트
     */
    @Transactional
    public ResponseEntity<TodoWithUsersResponseDto> readById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);

        //해당 일정 담당하는 유저 찾아서 리스트로
        List<UserTodo> userTodoList = userTodoRepository.findByTodo_Id(id);
        List<User> userList = userTodoList.stream()
                .map(UserTodo::getUser)
                .toList();
        List<UserWithoutDateResponseDto> userWithoutDateResponseDtoList = userList.stream()
                .map(UserWithoutDateResponseDto::new)
                .toList();

        return ResponseEntity.ok(new TodoWithUsersResponseDto(todoResponseDto, userWithoutDateResponseDtoList));
    }

    /**
     * 일정 수정 : 일정 제목, 일정 내용이 모두 입력되지 않아도 동작하게
     *
     * @param requestDto 수정할 일정 요청 Dto
     * @param id         수정할 일정 Id
     * @return 수정된 일정 내용 응답 Dto
     */
    @Transactional
    public ResponseEntity<TodoResponseDto> update(TodoRequestDto requestDto, Long id) {
        Todo foundTodo = todoRepository.findById(id).orElseThrow();
        if (requestDto.getTodoTitle() != null) {
            foundTodo.setTodoTitle(requestDto.getTodoTitle());
        }
        if (requestDto.getTodoContents() != null) {
            foundTodo.setTodoContents(requestDto.getTodoContents());
        }

        Todo savedTodo = todoRepository.save(foundTodo);
        return ResponseEntity.ok(new TodoResponseDto(savedTodo));
    }

    /**
     * 수정된 날짜 기준 내림차순으로 일정 전체 조회(페이지네이션)
     *
     * @param pageable (PageNumber, PageSize), 전부 안 들어올 시 기본값 설정 (0, 10)
     * @return 일정 전체 조회 페이지
     */
    @Transactional
    public ResponseEntity<Page<TodoResponseDto>> readAll(Pageable pageable) {
        int page = (pageable != null && pageable.getPageNumber() >= 0) ? pageable.getPageNumber() : 0;
        int size = (pageable != null && pageable.getPageSize() > 0) ? pageable.getPageSize() : 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");

        Pageable sortedPageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(todoRepository.findAll(sortedPageable).map(TodoResponseDto::new));
    }

    /**
     * 일정 삭제
     *
     * @param id 삭제할 일정 Id
     * @return 삭제된 내용 응답 Dto
     */
    @Transactional
    public ResponseEntity<TodoResponseDto> delete(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todo);
        return ResponseEntity.ok(new TodoResponseDto(todo));
    }
}
