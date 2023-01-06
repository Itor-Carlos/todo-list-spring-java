package com.ifba.dev.todolist.repositories.implem;

import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.model.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TodoRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Todo> find(Long id, String name, String descricao, TodoStatus todoStatus){

        StringBuilder jpql = new StringBuilder();
        jpql.append("from Todo WHERE 0 = 0 ");

        Map<String,Object> mapaParametros = new HashMap<>();

        if(id != null){
            jpql.append("and id = :id ");
            mapaParametros.put("id",id);
        }
        if(name != null){
            jpql.append("and name LIKE :name ");
            mapaParametros.put("name","%"+name+"%");
        }
        if(descricao != null){
            jpql.append("and descricao LIKE :descricao");
            mapaParametros.put("descricao","%"+descricao+"%");
        }

        if(todoStatus != null){
            jpql.append("and todoStatus LIKE :todoStatus");
            switch (todoStatus){
                case PENDENTE: mapaParametros.put("todoStatus",TodoStatus.PENDENTE);break;
                case CONCLUIDO: mapaParametros.put("todoStatus",TodoStatus.CONCLUIDO);break;
            }
        }

        TypedQuery<Todo> query = this.entityManager.createQuery(jpql.toString(),Todo.class);

        mapaParametros.forEach((chave,valor) -> query.setParameter(chave,valor));
        return query.getResultList();
    }

    public void deleteById(UUID id){
        Query query = entityManager.createQuery(("Delete FROM Todo WHERE id LIKE :id"));
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public void updateTodo(UUID id, Todo todo){
        StringBuilder jpql = new StringBuilder();
        jpql.append("UPDATE Todo SET id = :id ");
        Map<String,Object> parametros = new HashMap<>();

        if(todo.getName() != null){
            jpql.append(", name = :name");
            parametros.put("name",todo.getName());
        }

        if(todo.getDescricao() != null){
            jpql.append(", descricao = :descricao");
            parametros.put("descricao",todo.getDescricao());
        }
        if(todo.getTodoStatus() != null){
            jpql.append(", todoStatus = :status");
            switch (todo.getTodoStatus()){
                case PENDENTE: parametros.put("status",TodoStatus.PENDENTE);break;
                case CONCLUIDO: parametros.put("status",TodoStatus.CONCLUIDO);break;
            }
        }

        jpql.append(" WHERE id = :id");
        parametros.put("id",id);

        Query queryUpdate = this.entityManager.createQuery(jpql.toString());
        parametros.forEach((key,value) -> queryUpdate.setParameter(key,value));
        queryUpdate.executeUpdate();
    }

    public Todo findById(UUID id){
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Todo Where id LIKE :id");

        TypedQuery<Todo> searchQuery = this.entityManager.createQuery(jpql.toString(), Todo.class);
        searchQuery.setParameter("id", id);
        return searchQuery.getResultList().get(0);
    }
}
