package com.ifba.dev.todolist.handler;


import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.exceptions.EntityNotFoundExceptionDetails;
import com.ifba.dev.todolist.exceptions.IllegalArgumentExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionDetails> todoNotFound(EntityNotFoundException entityNotFoundException){
        EntityNotFoundExceptionDetails todoNotFound = new EntityNotFoundExceptionDetails(
                "Todo not found in this id",
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "Entity Not Found"
        );
        return new ResponseEntity<>(todoNotFound,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionDetails> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        IllegalArgumentExceptionDetails illegalArgument = new IllegalArgumentExceptionDetails(
                illegalArgumentException.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Illegal Argument"
        );
        return new ResponseEntity<>(illegalArgument,HttpStatus.BAD_REQUEST);
    }

}
