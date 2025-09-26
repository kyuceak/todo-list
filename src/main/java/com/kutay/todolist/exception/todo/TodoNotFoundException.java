package com.kutay.todolist.exception.todo;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message){
        super(message);
    }
}
