package com.ifba.dev.todolist.controllers;

import com.ifba.dev.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;
    

}
