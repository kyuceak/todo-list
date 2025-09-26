package com.kutay.todolist.DTO;

//public class TodoResponseDTO {
//
//
//    private Long id;
//    private String title;
//    private boolean completed;
//    private String description;
//
//
//    public TodoResponseDTO(Long id,boolean completed, String description, String title) {
//        this.id = id;
//        this.completed = completed;
//        this.description = description;
//        this.title = title;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(boolean completed) {
//        this.completed = completed;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//}

public record TodoResponseDTO (
        Long id,
        boolean completed,
        String description,
        String title
){

}