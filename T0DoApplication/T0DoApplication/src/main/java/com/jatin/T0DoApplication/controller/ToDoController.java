package com.jatin.T0DoApplication.controller;


import com.jatin.T0DoApplication.Repository.ToDoRepository;
import com.jatin.T0DoApplication.dto.ToDoRequest;
import com.jatin.T0DoApplication.dto.ToDoResponse;
import com.jatin.T0DoApplication.model.ToDo;
import com.jatin.T0DoApplication.model.TodoStatus;
import com.jatin.T0DoApplication.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final TodoService todoService;
    private final ToDoRepository toDoRepository;

    @PostMapping
    public ResponseEntity<ToDoResponse> createTodo(@RequestBody ToDoRequest toDoRequest){

        ToDo toDo= ToDo.builder()
                .createdAt(OffsetDateTime.now())
                .title(toDoRequest.getTitle())
                .status(TodoStatus.OPEN)
                .build();
        ToDo saved=toDoRepository.save(toDo);

        ToDoResponse response=todoService.mapToResponse(toDo);
        return ResponseEntity.ok(response);

    }


    @GetMapping
    public ResponseEntity<List<ToDoResponse>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponse> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
