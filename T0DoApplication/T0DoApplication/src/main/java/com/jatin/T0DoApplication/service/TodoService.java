package com.jatin.T0DoApplication.service;

import com.jatin.T0DoApplication.dto.ToDoResponse;
import com.jatin.T0DoApplication.model.ToDo;

import java.util.List;

public interface TodoService {

    List<ToDoResponse> getAllTodos();

    ToDoResponse getTodoById(Long id);

    void deleteTodo(Long id);

    public ToDoResponse mapToResponse(ToDo todo);
}
