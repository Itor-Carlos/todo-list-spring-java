package com.ifba.dev.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundExceptionDetails extends ExceptionDetails{

    private String message;

    public EntityNotFoundExceptionDetails() {
    }

    public EntityNotFoundExceptionDetails(int httpStatus, LocalDateTime timeStamp, String exception, String message) {
        super(httpStatus, timeStamp, exception);
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
