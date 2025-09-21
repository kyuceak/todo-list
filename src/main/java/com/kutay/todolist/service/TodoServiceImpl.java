package com.kutay.todolist.service;

import com.kutay.todolist.model.Todo;
import com.kutay.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoServiceImpl implements TodoService{


    private TodoRepository todoRepository;


    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository)
    {
        this.todoRepository = todoRepository;
    }


    @Override
    public List<Todo> findAll() {

        return todoRepository.findAll();
    }

    @Override
    @Transactional
    public Todo save(Todo todo) {
       return todoRepository.save(todo);
    }

    @Override
    public Todo findById(Long id) {

        Optional<Todo> result = todoRepository.findById(id);

        Todo todo = null;

        if(result.isPresent()){
           todo = result.get();
        }else {
            throw new RuntimeException("cant find the employee.");
        }

        return todo;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> searchByTitle(String title) {
        return todoRepository.findAllByTitle(title);
    }


    @Override
    public List<Todo> getCompleted() {
        return todoRepository.fetchAllCompleted();
    }

    @Override
    public List<Todo> getUncompleted() {
        return todoRepository.fetchAllunCompleted();
    }
}
