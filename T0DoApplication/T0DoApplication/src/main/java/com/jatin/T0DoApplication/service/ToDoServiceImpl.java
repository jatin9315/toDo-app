package com.jatin.T0DoApplication.service;

import com.jatin.T0DoApplication.Exception.ResourceNotFoundException;
import com.jatin.T0DoApplication.Repository.ToDoRepository;
import com.jatin.T0DoApplication.dto.ToDoResponse;
import com.jatin.T0DoApplication.model.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements TodoService {

    private final ToDoRepository todoRepository;

    @Override
    public List<ToDoResponse> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ToDoResponse getTodoById(Long id) {
        ToDo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        return mapToResponse(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        ToDo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todoRepository.delete(todo);
    }

    @Override
    public ToDoResponse mapToResponse(ToDo todo) {
        return ToDoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
