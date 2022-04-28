package com.ifba.dev.todolist.handler;


import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.exceptions.EntityNotFoundExceptionDetails;
import com.ifba.dev.todolist.exceptions.IllegalArgumentExceptionDetails;
import com.ifba.dev.todolist.exceptions.MethodArgumentNotValidExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionDetails> todoNotFound(EntityNotFoundException entityNotFoundException){
        EntityNotFoundExceptionDetails todoNotFound = new EntityNotFoundExceptionDetails(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "Entity Not Found",
                "Todo not found in this id"
        );
        return new ResponseEntity<>(todoNotFound,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionDetails> illegalArgumentException(IllegalArgumentException illegalArgumentException){
        IllegalArgumentExceptionDetails illegalArgument = new IllegalArgumentExceptionDetails(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Illegal Argument",
                illegalArgumentException.getMessage()
        );
        return new ResponseEntity<>(illegalArgument,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MethodArgumentNotValidExceptionDetails> methodArgumentException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String fieldMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining());
        String fieldError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining());

        MethodArgumentNotValidExceptionDetails methodArgumentException = new MethodArgumentNotValidExceptionDetails(
                fieldMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Method Argument Not Valid Exception",
                fieldError
        );
        return new ResponseEntity<>(methodArgumentException,HttpStatus.BAD_REQUEST);
    }

}
