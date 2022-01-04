package com.ifba.dev.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentExceptionDetails extends ExceptionDetails{
    public IllegalArgumentExceptionDetails() {
    }

    public IllegalArgumentExceptionDetails(String message, int httpStatus, LocalDateTime timeStamp, String exception) {
        super(message, httpStatus, timeStamp, exception);
    }
}
