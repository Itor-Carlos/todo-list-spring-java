package com.ifba.dev.todolist.repositories.interfaces;

import com.ifba.dev.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {



    public List<Todo> find(Long id, String name, String descricao);

    public void deleteById(Long id);


}
