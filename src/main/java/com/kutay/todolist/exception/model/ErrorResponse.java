package com.kutay.todolist.exception.model;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
}
