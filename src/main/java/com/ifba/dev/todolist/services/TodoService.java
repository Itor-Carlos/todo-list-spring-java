package com.ifba.dev.todolist.services;

import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.repositories.TodoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        if(!optionalTodo.isPresent()){
            throw new NoSuchElementException("Todo not found");
        }
        return optionalTodo.get();
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
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        Optional<Todo> optionalTodo = this.todoRepository.findById(id);
        this.todoRepository.delete(optionalTodo.get());
    }

    public Todo alterar(Todo todo, Long id){

        if(todo == null){
            throw new NoSuchElementException("todo nao pode ser null");
        }

        if(todo.getDescricao() == null){
            throw new IllegalArgumentException("a descricao do todo nao pode ser nula");
        }
        if(todo.getDescricao() == ""){
            throw new IllegalArgumentException("a descricao do todo nao pode estar vazia");
        }
        if(todo.getName() == null){
            throw new IllegalArgumentException("o nome nao pode ser nulo");
        }
        if(todo.getName() == ""){
            throw new IllegalArgumentException("o nome não pode estar vazio");
        }

        Todo todoModificado = this.todoRepository.searchForId(id);

        if(todoModificado == null){
            throw new EntityNotFoundException("nao foi encontrado nenhum Todo no id passado");
        }

        BeanUtils.copyProperties(todo,todoModificado,"id");
        return this.todoRepository.save(todoModificado);
    }



}