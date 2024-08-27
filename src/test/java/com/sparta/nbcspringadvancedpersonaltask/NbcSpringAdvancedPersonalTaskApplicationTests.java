package com.sparta.nbcspringadvancedpersonaltask;

import com.sparta.nbcspringadvancedpersonaltask.dto.TodoRequestDto;
import com.sparta.nbcspringadvancedpersonaltask.dto.TodoResponseDto;
import com.sparta.nbcspringadvancedpersonaltask.entity.Todo;
import com.sparta.nbcspringadvancedpersonaltask.repository.TodoRepository;
import com.sparta.nbcspringadvancedpersonaltask.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class NbcSpringAdvancedPersonalTaskApplicationTests {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodo() {
        // given
        TodoRequestDto requestDto = new TodoRequestDto();
        Todo todo = new Todo(requestDto);

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        // when
        TodoResponseDto responseDto = todoService.create(requestDto);

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getUsername()).isEqualTo(requestDto.getUsername());
        assertThat(responseDto.getTodoTitle()).isEqualTo(requestDto.getTodoTitle());
        assertThat(responseDto.getTodoContents()).isEqualTo(requestDto.getTodoContents());
    }

    @Test
    public void testReadTodoById() {
        // given
        Long id = 1L;
        Todo todo = new Todo();
        todo.setId(id);
        todo.setUsername("testUser");
        todo.setTodoTitle("Test Title");
        todo.setTodoContents("Test Content");

        when(todoRepository.findById(id)).thenReturn(Optional.of(todo));

        // when
        TodoResponseDto responseDto = todoService.readTodoById(id);

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getUsername()).isEqualTo(todo.getUsername());
        assertThat(responseDto.getTodoTitle()).isEqualTo(todo.getTodoTitle());
        assertThat(responseDto.getTodoContents()).isEqualTo(todo.getTodoContents());
    }

    @Test
    public void testUpdateTodoViaId() {
        // given
        Long id = 1L;
        Todo existingTodo = new Todo();
        existingTodo.setId(id);
        existingTodo.setUsername("oldUser");
        existingTodo.setTodoTitle("Old Title");
        existingTodo.setTodoContents("Old Content");

        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setUsername("newUser");
        requestDto.setTodoContents("New Content");

        when(todoRepository.findById(id)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(existingTodo);

        // when
        TodoResponseDto responseDto = todoService.update(requestDto, id);

        // then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getUsername()).isEqualTo(requestDto.getUsername());
        assertThat(responseDto.getTodoTitle()).isEqualTo(existingTodo.getTodoTitle());
        assertThat(responseDto.getTodoContents()).isEqualTo(requestDto.getTodoContents());
    }

}
