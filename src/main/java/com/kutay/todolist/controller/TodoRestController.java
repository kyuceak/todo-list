package com.kutay.todolist.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kutay.todolist.model.Todo;
import com.kutay.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class TodoRestController {



    private TodoService todoService;
    private ObjectMapper objectMapper;


    @Autowired
    public TodoRestController(TodoService todoService, ObjectMapper objectMapper){
        this.todoService = todoService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/todos")
    public List<Todo> getTodos(){
        return todoService.findAll();
    }

    @GetMapping("/todos/{todoId}")
    public Todo getTodo(@PathVariable long todoId){
        return todoService.findById(todoId);
    }

    @GetMapping("/todos/completed")
    public List<Todo> getCompletedTodos(){
        return todoService.getCompleted();
    }

    @GetMapping("/todos/uncompleted")
    public List<Todo> getUncompletedTodos(){
        return todoService.getUncompleted();
    }

    @GetMapping("/todos/search")
    public List<Todo> searchByTitle(@RequestParam String title) {
        return todoService.searchByTitle(title);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo){

        todo.setId(null);
        System.out.println(todo);
        return todoService.save(todo);
    }

    @PutMapping("/todos")
    public Todo updateTodo(@RequestBody Todo todo){

        return todoService.save(todo);
    }

    @PatchMapping("/todos/{todoId}")
    public Todo patchTodo(@PathVariable Long todoId, @RequestBody Map<String, Object> patchPayload){
        Todo tempTodo = todoService.findById(todoId);

        if (tempTodo == null){
            throw new RuntimeException("Todo not found");
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Todo id not allowed in patch requests");
        }

        Todo patchedTodo = apply(patchPayload,tempTodo);

        Todo dbTodo = todoService.save(patchedTodo);

        return dbTodo;

    }

    private Todo apply(Map<String, Object> patchPayload, Todo tempTodo) {

        ObjectNode todoNode = objectMapper.convertValue(tempTodo, ObjectNode.class);

        ObjectNode payloadNode = objectMapper.convertValue(patchPayload, ObjectNode.class);


        todoNode.setAll(payloadNode);



        return objectMapper.convertValue(todoNode, Todo.class);

    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoService.deleteById(todoId);
    }










}
