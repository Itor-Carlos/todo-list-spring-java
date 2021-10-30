package com.ifba.dev.todolist.services;

import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll(){
        return this.todoRepository.findAll();
    }

    public Todo buscar(Long id){
        Todo todo = this.todoRepository.getById(id);
        if(todo == null){
            throw new EntityNotFoundException("não foi encontrado nenhum Todo com o id passado");
        }
        return todo;
    }



}
