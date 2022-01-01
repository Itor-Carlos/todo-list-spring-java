package com.ifba.dev.todolist.controllers;

import com.ifba.dev.todolist.dto.TodoDTO;
import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.model.Todo;
import com.ifba.dev.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> listar(){
        List<Todo> listaGeral = this.todoService.getAll();
        return ResponseEntity.ok(listaGeral);
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody TodoDTO todoDTO){
        try{
            Todo todoSalvo = this.todoService.salvar(todoDTO.toTodo());
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

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody TodoDTO todoDTO, @PathVariable("id") Long id){
        try{
            this.todoService.alterar(todoDTO.toTodo(),id);
            return ResponseEntity.ok().build();
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

    @GetMapping(path = "/pesquisa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@RequestParam(name = "id",required = false) Long id, @RequestParam(name = "name",required = false) String name, @RequestParam(name = "descricao",required = false) String descricao, @RequestParam(name = "todoStatus",required = false)TodoStatus todoStatus){
        try{
            List<Todo> listaResultado = this.todoService.find(id,name,descricao,todoStatus);
            return ResponseEntity.ok(listaResultado);
        }
        catch (IllegalArgumentException errorIllegalArgument){
            return ResponseEntity.badRequest().body(errorIllegalArgument.getMessage());
        }
    }
}
