package com.kutay.todolist.service;

import com.kutay.todolist.model.Todo;

import java.util.List;

public interface TodoService {


    List<Todo> findAll();
    Todo save(Todo todo);
    Todo findById(Long id);
    void deleteById(Long id);
    List<Todo> searchByTitle(String title);
    List<Todo> getCompleted();
    List<Todo> getUncompleted();

}
