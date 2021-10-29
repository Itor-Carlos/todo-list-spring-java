package com.ifba.dev.todolist.model;

public class Todo {

    private Long id;
    private String name;
    private String descricao;

    public Todo(){}

    public Todo(Long id, String name, String descricao){
        this.id = id;
        this.name = name;
        this.descricao = descricao;
    }

}
