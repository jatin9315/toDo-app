package com.jatin.T0DoApplication.service;

import com.jatin.T0DoApplication.Exception.ResourceNotFoundException;
import com.jatin.T0DoApplication.Repository.ToDoRepository;
import com.jatin.T0DoApplication.dto.ToDoResponse;
import com.jatin.T0DoApplication.model.ToDo;
import com.jatin.T0DoApplication.model.TodoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository todoRepository;

    @InjectMocks
    private ToDoServiceImpl todoService;

    private ToDo todo;

    @BeforeEach
    void setup() {
        todo = ToDo.builder()
                .id(1L)
                .title("Learn Spring Boot")
                .status(TodoStatus.OPEN)
                .createdAt(OffsetDateTime.now())
                .build();
    }

    @Test
    void shouldReturnAllTodos() {
        when(todoRepository.findAll()).thenReturn(List.of(todo));
        List<ToDoResponse> result = todoService.getAllTodos();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Learn Spring Boot", result.get(0).getTitle());
        assertEquals(TodoStatus.OPEN, result.get(0).getStatus());
        verify(todoRepository, times(1)).findAll();
    }
    @Test
    void shouldReturnEmptyListWhenNoTodosExist() {
        when(todoRepository.findAll()).thenReturn(List.of());
        List<ToDoResponse> result = todoService.getAllTodos();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(todoRepository, times(1)).findAll();
    }
    @Test
    void shouldReturnTodoById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        ToDoResponse result = todoService.getTodoById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Learn Spring Boot", result.getTitle());
        assertEquals(TodoStatus.OPEN, result.getStatus());
        verify(todoRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTodoNotFoundById() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> todoService.getTodoById(1L)
        );
        assertEquals("Todo not found with id: 1", exception.getMessage());
        verify(todoRepository, times(1)).findById(1L);
    }
    @Test
    void shouldDeleteTodoSuccessfully() {
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        todoService.deleteTodo(1L);
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, times(1)).delete(todo);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingTodo() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> todoService.deleteTodo(1L)
        );
        assertEquals("Todo not found with id: 1", exception.getMessage());
        verify(todoRepository, times(1)).findById(1L);
        verify(todoRepository, never()).delete(any(ToDo.class));
    }

}
