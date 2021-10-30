package com.ifba.dev.todolist.controllers;

import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> listar(){
        List<Todo> listaGeral = this.todoService.getAll();
        return ResponseEntity.ok(listaGeral);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> buscar(@PathVariable("id") Long id){
        try{
            Todo todo = this.todoService.buscar(id);
            return ResponseEntity.ok(todo);
        }
        catch (NoSuchElementException errorNotFound){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Todo todo){
        try{
            Todo todoSalvo = this.todoService.salvar(todo);
            URI location = URI.create("/todos/"+todoSalvo.getId());
            return ResponseEntity.created(location).body(todoSalvo);
        }
        catch (IllegalArgumentException errorArguments){
            return ResponseEntity.badRequest().body(errorArguments.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deletar(@PathVariable("id") Long id){
        try{
            this.todoService.deletar(id);
            return ResponseEntity.ok().build();
        }
        catch(EntityNotFoundException erro){
            return ResponseEntity.notFound().build();
        }
    }

}
