package com.ifba.dev.todolist.services;

import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll(){
        return this.todoRepository.findAll();
    }

    public Todo buscar(Long id){
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        Todo todo = optionalTodo.get();
        return todo;
    }

    public Todo salvar(Todo todo){
        if(todo.getDescricao() == null){
            throw new IllegalArgumentException("A descrição não pode ser nulo");
        }
        if(todo.getDescricao() == ""){
            throw new IllegalArgumentException("A descrição não pode estar vazio");
        }

        if(todo.getName() == null){
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        if(todo.getName() == ""){
            throw new IllegalArgumentException("O nome não pode estar vazio");
        }

        return this.todoRepository.save(todo);
    }

    public void deletar(Long id){
        Todo todo = this.todoRepository.searchForId(id);
        if (todo == null) {
            throw new EntityNotFoundException("não foi encontrado nenhum todo com o id registrado");
        }
        this.todoRepository.delete(todo);
    }

}