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


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAll(){
        return this.todoRepository.findAll();
    }

    public Todo buscar(Long id){
        if(id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        return this.todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
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
        this.todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        this.todoRepository.deleteById(id);
    }

    public void alterar(Todo todo, Long id){
        Optional<Todo> optionalTodoModificado = this.todoRepository.findById(id);
        if(!optionalTodoModificado.isPresent()){
            throw new EntityNotFoundException("nao foi encontrado nenhum Todo no id passado");
        }
        this.todoRepository.updateTodo(id,todo);
    }

    public List<Todo> find(Long id, String name, String descricao, TodoStatus todoStatus){
        if(id != null && id < 1){
            throw new IllegalArgumentException("the id most be higher or equals 1");
        }
        return this.todoRepository.find(id,name,descricao,todoStatus);
    }


}