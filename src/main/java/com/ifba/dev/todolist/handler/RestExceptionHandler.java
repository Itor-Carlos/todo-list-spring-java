package com.ifba.dev.todolist.handler;


import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.exceptions.EntityNotFoundExceptionDetails;
import com.ifba.dev.todolist.exceptions.IllegalArgumentExceptionDetails;
import com.ifba.dev.todolist.exceptions.TodoFieldNotValidExceptionDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice(basePackages = "com.ifba.dev.todolist.controllers")
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
    public ResponseEntity<TodoFieldNotValidExceptionDetails> todoFieldNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){

        List<FieldError> fieldErrorList = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> fieldMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());//get the message of this errors
        List<String> fieldError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.toList());;//get the name of field of this erros

        Map<String,String> messageErrors = new HashMap<>();

        for(int i = 0; i<fieldErrorList.size();i++){
            messageErrors.put(fieldError.get(i), fieldMessage.get(i));
        }

        TodoFieldNotValidExceptionDetails todoFieldNotValidExceptionDetails = new TodoFieldNotValidExceptionDetails(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "TodoFieldNotValidExceptionDetails", messageErrors);
        
        return new ResponseEntity<>(todoFieldNotValidExceptionDetails,HttpStatus.BAD_REQUEST);
    }

}
