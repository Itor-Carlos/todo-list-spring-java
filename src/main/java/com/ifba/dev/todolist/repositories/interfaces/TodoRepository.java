package com.ifba.dev.todolist.repositories.interfaces;

import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {



    public List<Todo> find(UUID id, String name, String descricao, TodoStatus todoStatus);

    @Transactional
    public void deleteById(UUID id);

    @Transactional
    public void updateTodo(UUID id, Todo todo);

    public Todo findById(UUID id);

}
