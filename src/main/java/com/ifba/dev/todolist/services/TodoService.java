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

    public List<Todo> findByNameContaining(String name){
        if(name == ""){
            throw new IllegalArgumentException("the name cannot be empty");
        }
        if(name == null){
            throw new IllegalArgumentException("the name cannot be null");
        }
        return this.todoRepository.findByNameContaining(name);
    }

    public List<Todo> findByDescricaoContaining(String descricao){
        if (descricao == ""){
            throw new IllegalArgumentException("the descricao cannot be empty");
        }
        if(descricao == null){
            throw new IllegalArgumentException("the descricao cannot be null");
        }
        return this.todoRepository.findByDescricaoContaining(descricao);
    }

}