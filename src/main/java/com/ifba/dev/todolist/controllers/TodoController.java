package com.ifba.dev.todolist.controllers;

import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        try{
            Todo todo = this.todoService.buscar(id);
            return ResponseEntity.ok(todo);
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
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
    public ResponseEntity<?> deletar(@PathVariable("id") Long id){
        try{
            this.todoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        catch(NoSuchElementException erro){
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@RequestBody Todo todo, @PathVariable("id") Long id){
        try{
            Todo todoModificado = this.todoService.alterar(todo,id);
            return ResponseEntity.ok(todoModificado);
        }
        catch(IllegalArgumentException errorArguments){
            return ResponseEntity.badRequest().body(errorArguments.getMessage());
        }
        catch (NoSuchElementException error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
        catch (EntityNotFoundException errorNotFound){
            return ResponseEntity.badRequest().body(errorNotFound.getMessage());
        }
    }
}
