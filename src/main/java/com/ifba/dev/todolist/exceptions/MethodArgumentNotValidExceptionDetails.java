package com.ifba.dev.todolist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MethodArgumentNotValidExceptionDetails extends ExceptionDetails{
    private String fieldError;

    public MethodArgumentNotValidExceptionDetails(String fieldError) {
        this.fieldError = fieldError;
    }

    public MethodArgumentNotValidExceptionDetails(String message, int httpStatus, LocalDateTime timeStamp, String exception, String fieldError) {
        super(message, httpStatus, timeStamp, exception);
        this.fieldError = fieldError;
    }

    public String getFieldError() {
        return fieldError;
    }

    public void setFieldError(String fieldError) {
        this.fieldError = fieldError;
    }
}
