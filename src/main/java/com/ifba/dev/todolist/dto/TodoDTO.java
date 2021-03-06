package com.ifba.dev.todolist.dto;

import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.model.Todo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoDTO {

    @NotBlank(message = "The name cannot be null")
    private String name;
    @NotNull(message = "The descricao cannot be null")
    private String descricao;
    @NotNull(message = "The todoStatus cannot be null")
    private TodoStatus todoStatus;

    public TodoDTO() {
    }

    public TodoDTO(String name, String descricao, TodoStatus todoStatus) {
        this.name = name;
        this.descricao = descricao;
        this.todoStatus = todoStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public Todo toTodo(){
        return new Todo(this.getName(),this.getDescricao(),this.getTodoStatus());
    }
}
