package com.ifba.dev.todolist.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//return a HttpStatus Code equals 
public class TodoFieldNotValidExceptionDetails extends ExceptionDetails{
    
    private Map<String,String> messages;//this attribute will be used to map the erros in the POST and PUT requests

    public TodoFieldNotValidExceptionDetails(int httpStatus, LocalDateTime timeStamp, String exception, Map<String, String> messages) {
        super(httpStatus, timeStamp, exception);
        this.messages = messages;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
