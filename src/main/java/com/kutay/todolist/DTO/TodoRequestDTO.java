package com.kutay.todolist.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//public class TodoRequestDTO {
//
//
//    @NotBlank(message="Title cannot be empty")
//    private String title;
//
//    @Size(max=255, message="Description must be less than 255 characters")
//    private String description;
//
//    private boolean completed;
//
//    public TodoRequestDTO(boolean completed, String description, String title) {
//        this.completed = completed;
//        this.description = description;
//        this.title = title;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public String getTitle() {
//        return title;
//    }
//
//}


public record TodoRequestDTO (
        boolean completed,
        String description,
        String title
        ) {


}