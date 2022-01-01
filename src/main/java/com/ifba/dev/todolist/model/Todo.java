package com.ifba.dev.todolist.model;

import com.ifba.dev.todolist.enums.TodoStatus;

import javax.persistence.*;

@Entity
@Table(name="todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "descricao",nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(length = 10,nullable = false)
    private TodoStatus todoStatus;

    public Todo(){}

    public Todo(String name, String descricao, TodoStatus todoStatus) {
        this.name = name;
        this.descricao = descricao;
        this.todoStatus = todoStatus;
    }

    public Todo(Long id, String name, String descricao, TodoStatus todoStatus){
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.todoStatus = todoStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
