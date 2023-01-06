package com.ifba.dev.todolist.controllers;

import com.ifba.dev.todolist.dto.TodoDTO;
import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.exceptions.EntityNotFoundException;
import com.ifba.dev.todolist.exceptions.EntityNotFoundExceptionDetails;
import com.ifba.dev.todolist.exceptions.IllegalArgumentExceptionDetails;
import com.ifba.dev.todolist.exceptions.TodoFieldNotValidExceptionDetails;
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
import java.util.UUID;


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

    @Operation(summary = "Search a specific Todo using id as a parameter")//describe the objective of this method
    @ApiResponses(value = {//maps the possible code states, their description, mediaType type and the returned object
        @ApiResponse(responseCode = "200", description = "Todo found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class))
        }),
        @ApiResponse(responseCode = "400", description = "Id not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = IllegalArgumentExceptionDetails.class))
        }),
        @ApiResponse(responseCode = "404", description = "Todo not found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundExceptionDetails.class))
        })
    })
    @GetMapping(path ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> buscar(@PathVariable("id") UUID id){
        Todo todo = this.todoService.buscar(id);
        return ResponseEntity.ok(todo);
    }
    @Operation(summary = "Create a Todo in database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Todo created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Todo.class))
        }),
        @ApiResponse(responseCode = "400", description = "Todo field is not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoFieldNotValidExceptionDetails.class))
        })
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> salvar(@RequestBody @Valid TodoDTO todoDTO){
        Todo todoSalvo = this.todoService.salvar(todoDTO.toTodo());
        URI location = URI.create("/todos/"+todoSalvo.getId());
        return ResponseEntity.created(location).body(todoSalvo);
    }

    @Operation(summary = "Delete a specific Todo usign id as a parameter")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Todo deleted", content = {
            @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "400", description = "Id not valid", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundExceptionDetails.class))
        }),
        @ApiResponse(responseCode = "404", description = "Todo not found. Operation cannot continue", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundExceptionDetails.class))
        })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable("id") UUID id){
        this.todoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Change an existing Place usign id as parameter search")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Changed attribute in specific Todo", content = {
            @Content(mediaType = "application/json")
        }),
        @ApiResponse(responseCode = "404", description = "Not found the Todo. Operation cannot continue", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EntityNotFoundExceptionDetails.class))
        })
    })
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@RequestBody TodoDTO todoDTO, @PathVariable("id") UUID id){
        this.todoService.alterar(todoDTO.toTodo(),id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Realize a search in database usign parameters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operation succeedd", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
        })
    })
    @GetMapping(path = "/pesquisa", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> find(@RequestParam(name = "id",required = false) Long id, @RequestParam(name = "name",required = false) String name, @RequestParam(name = "descricao",required = false) String descricao, @RequestParam(name = "todoStatus",required = false)TodoStatus todoStatus){
        List<Todo> listaResultado = this.todoService.find(id,name,descricao,todoStatus);
        return ResponseEntity.ok(listaResultado);
    }
}
