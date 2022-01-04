package com.ifba.dev.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptionDetails extends ExceptionDetails{
    public EntityNotFoundExceptionDetails() {
    }

    public EntityNotFoundExceptionDetails(String message, int httpStatus, LocalDateTime timeStamp, String exception) {
        super(message, httpStatus, timeStamp, exception);
    }
}
