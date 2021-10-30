package com.ifba.dev.todolist.services;

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


}
