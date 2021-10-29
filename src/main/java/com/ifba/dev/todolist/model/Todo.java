package com.ifba.dev.todolist.model;

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

    public Todo(){}

    public Todo(Long id, String name, String descricao){
        this.id = id;
        this.name = name;
        this.descricao = descricao;
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
}
