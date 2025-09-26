package com.kutay.todolist.exception.handler;


import com.kutay.todolist.exception.model.ErrorResponse;
import com.kutay.todolist.exception.todo.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleTodoNotFound(TodoNotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "NOT_FOUND",ex.getMessage(), LocalDateTime.now());
    }


}
