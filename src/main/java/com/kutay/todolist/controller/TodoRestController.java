package com.kutay.todolist.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kutay.todolist.DTO.TodoRequestDTO;
import com.kutay.todolist.DTO.TodoResponseDTO;
import com.kutay.todolist.model.Todo;
import com.kutay.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class TodoRestController {



    private final TodoService todoService;
    private final ObjectMapper objectMapper;


    @Autowired
    public TodoRestController(TodoService todoService, ObjectMapper objectMapper){
        this.todoService = todoService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/todos")
    public List<TodoResponseDTO> getTodos(){
        return todoService.findAll();
    }

    @GetMapping("/todos/{todoId}")
    public TodoResponseDTO getTodo(@PathVariable long todoId){
        return todoService.findById(todoId);
    }

    @GetMapping("/todos/completed")
    public List<TodoResponseDTO> getCompletedTodos(){
        return todoService.getCompleted();
    }

    @GetMapping("/todos/uncompleted")
    public List<TodoResponseDTO> getUncompletedTodos(){
        return todoService.getUncompleted();
    }

    @GetMapping("/todos/search")
    public List<TodoResponseDTO> searchByTitle(@RequestParam String title) {
        return todoService.searchByTitle(title);
    }

    @PostMapping("/todos")
    public TodoResponseDTO addTodo(@RequestBody TodoRequestDTO todo){



        System.out.println(todo);
        return todoService.save(todo);
    }

    @PutMapping("/todos")
    public TodoResponseDTO updateTodo(@RequestBody TodoRequestDTO todo){
        return todoService.save(todo);
    }

    @PatchMapping("/todos/{todoId}")
    public TodoResponseDTO patchTodo(@PathVariable Long todoId, @RequestBody Map<String, Object> patchPayload){
        // get the Todo who is going to be updated
        TodoResponseDTO tempTodo = todoService.findById(todoId);

        if (tempTodo == null){
            throw new RuntimeException("Todo not found");
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Todo id not allowed in patch requests");
        }

        // apply the changes to Todo
        TodoResponseDTO patchedTodo = apply(patchPayload,tempTodo);

        TodoResponseDTO dbTodo = todoService.save(new TodoRequestDTO(patchedTodo.completed(), patchedTodo.description(), patchedTodo.title()));

        return dbTodo;

    }

    private TodoResponseDTO apply(Map<String, Object> patchPayload, TodoResponseDTO tempTodo) {

        ObjectNode todoNode = objectMapper.convertValue(tempTodo, ObjectNode.class);

        ObjectNode payloadNode = objectMapper.convertValue(patchPayload, ObjectNode.class);


        todoNode.setAll(payloadNode);



        return objectMapper.convertValue(todoNode, TodoResponseDTO.class);

    }

    @DeleteMapping("/todos/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){
        todoService.deleteById(todoId);
    }










}
