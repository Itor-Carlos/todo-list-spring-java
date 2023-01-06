package com.ifba.dev.todolist.model;

import com.ifba.dev.todolist.enums.TodoStatus;

import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="todo")
public class Todo {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	private UUID id;

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

    public Todo(UUID id, String name, String descricao, TodoStatus todoStatus){
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.todoStatus = todoStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
