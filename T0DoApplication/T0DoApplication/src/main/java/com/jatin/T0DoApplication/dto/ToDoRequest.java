package com.jatin.T0DoApplication.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ToDoRequest {

    @NotNull
    public String title;

}
