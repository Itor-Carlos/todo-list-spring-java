package com.ifba.dev.todolist.services;

import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.repositories.interfaces.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll(){
        return this.todoRepository.findAll();
    }

    public Todo buscar(UUID id){
        Todo todoSearched = todoRepository.findById(id);
        if(todoSearched == null) throw new EntityNotFoundException();
        return todoSearched;
    }

    public Todo salvar(Todo todo){
        return this.todoRepository.save(todo);
    }

    public void deletar(UUID id){
        Todo todoSearched = this.todoRepository.findById(id);
        this.todoRepository.deleteById(id);
    }

    public void alterar(Todo todo, UUID id){
        Todo todoSearched = this.todoRepository.findById(id);
        if(todoSearched == null){
            throw new EntityNotFoundException("nao foi encontrado nenhum Todo no id passado");
        }
        this.todoRepository.updateTodo(id,todo);
    }

    public List<Todo> find(UUID id, String name, String descricao, TodoStatus todoStatus){
        return this.todoRepository.find(id,name,descricao,todoStatus);
    }


}