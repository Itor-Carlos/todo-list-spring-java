package com.ifba.dev.todolist.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException() {
    }

    public  EntityNotFoundException(String mensagem){
        super(mensagem);
    }

}
