package com.kutay.todolist.service;

import com.kutay.todolist.DTO.TodoRequestDTO;
import com.kutay.todolist.DTO.TodoResponseDTO;
import com.kutay.todolist.model.Todo;

import java.util.List;

public interface TodoService {


    List<TodoResponseDTO> findAll();
    TodoResponseDTO save(TodoRequestDTO dto);
    TodoResponseDTO findById(Long id);
    void deleteById(Long id);
    List<TodoResponseDTO> searchByTitle(String title);
    List<TodoResponseDTO> getCompleted();
    List<TodoResponseDTO> getUncompleted();

}
