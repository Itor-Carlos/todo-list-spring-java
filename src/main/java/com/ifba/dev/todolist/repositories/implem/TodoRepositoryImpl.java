package com.ifba.dev.todolist.repositories.implem;

import com.ifba.dev.todolist.enums.TodoStatus;
import com.ifba.dev.todolist.model.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if(todoStatus == TodoStatus.CONCLUIDO){
                mapaParametros.put("todoStatus",TodoStatus.CONCLUIDO);
            }
            if(todoStatus == TodoStatus.PENDENTE){
                mapaParametros.put("todoStatus",TodoStatus.PENDENTE);
            }
        }

        TypedQuery<Todo> query = this.entityManager.createQuery(jpql.toString(),Todo.class);

        mapaParametros.forEach((chave,valor) -> query.setParameter(chave,valor));
        return query.getResultList();
    }

    public void deleteById(Long id){
        Query query = entityManager.createQuery(("Delete FROM Todo WHERE id = " + id));
    }

}
