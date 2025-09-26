package com.kutay.todolist.exception.todo;



public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message){
        super(message);
    }
}
