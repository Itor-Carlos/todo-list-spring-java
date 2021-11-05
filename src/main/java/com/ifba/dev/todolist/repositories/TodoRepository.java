package com.ifba.dev.todolist.repositories;

import com.ifba.dev.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("select t FROM Todo t WHERE t.id=:id")
    Todo searchForId(@Param("id") Long id);

    List<Todo> findByNameContaining(String name);

}
