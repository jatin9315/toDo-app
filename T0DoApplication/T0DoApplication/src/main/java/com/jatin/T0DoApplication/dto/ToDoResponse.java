package com.jatin.T0DoApplication.dto;

import com.jatin.T0DoApplication.model.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoResponse {

    private Long id;
    private String title;
    private TodoStatus status;
    @CreationTimestamp
    private OffsetDateTime createdAt;
}
