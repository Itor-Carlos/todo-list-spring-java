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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
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

    @Operation(summary = "Get All Todos")//describe the objective of this method
    @ApiResponses(value = {//maps the possible code states, their description, mediaType type and the returned object
        @ApiResponse(responseCode = "200", description = "Operation succeeded", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class))
        })
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Todo>> listar(){
        List<Todo> listaGeral = this.todoService.getAll();
        return ResponseEntity.ok(listaGeral);
    }

    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id){
        Todo todo = this.todoService.buscar(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody @Valid TodoDTO todoDTO){
        Todo todoSalvo = this.todoService.salvar(todoDTO.toTodo());
        URI location = URI.create("/todos/"+todoSalvo.getId());
        return ResponseEntity.created(location).body(todoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id){
        this.todoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody TodoDTO todoDTO, @PathVariable("id") Long id){
        this.todoService.alterar(todoDTO.toTodo(),id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/pesquisa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@RequestParam(name = "id",required = false) Long id, @RequestParam(name = "name",required = false) String name, @RequestParam(name = "descricao",required = false) String descricao, @RequestParam(name = "todoStatus",required = false)TodoStatus todoStatus){
        List<Todo> listaResultado = this.todoService.find(id,name,descricao,todoStatus);
        return ResponseEntity.ok(listaResultado);
    }
}
